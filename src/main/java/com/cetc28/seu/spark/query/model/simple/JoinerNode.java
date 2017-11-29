package com.cetc28.seu.spark.query.model.simple;

import org.apache.hadoop.hbase.client.Result;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaPairRDD;

import org.apache.spark.api.java.function.PairFunction;

import com.cetc28.seu.rdf.RDF;
import com.cetc28.seu.rdf.Term;
import com.cetc28.seu.spark.query.model.InnerNode;
import com.cetc28.seu.spark.query.result.DistributedResultSet;


import scala.Tuple2;

enum  returnValue{left,right};// 表示返回值来自左孩子还是右孩子
public class JoinerNode extends InnerNode {

	private static final long serialVersionUID = -5515459951672424727L;
	private final static String family = "objects";
	private String joinColumn;
	
	private returnValue returnV;
	public  JoinerNode() {
		
	}
	
	public JoinerNode(int id, int parent, Term term, String joinColumn) {
		super(id, -1, -1, parent, term);
		this.joinColumn=joinColumn;
	}

	public JoinerNode(int id, int parent, Term childTerm, RDF childRDF, String joinColumn) {
		super(id, -1, -1, parent, childTerm);
		constructReturn(childRDF);
		this.joinColumn=joinColumn;
	}

	public void constructReturn(RDF rdf){
		if(this.getTerm().getValue().equals(rdf.getSubject().getValue())){
			returnV=returnValue.left;
		}else{
			returnV=returnValue.right;
		}
	}
	@Override
	public DistributedResultSet query() {
		long start =System.currentTimeMillis();
		JavaPairRDD<String,Result> rddByJoinAttr=((DistributedResultSet) this.getLelfInputResult()).getResultRDD().mapToPair(new PairFunction<Tuple2<String,Result>, String, Result>() {
			private static final long serialVersionUID = -7885182916432221138L;

			@Override
			public Tuple2<String, Result> call(Tuple2<String, Result> t) throws Exception {
				byte[] key=t._2.getValue(Bytes.toBytes(family), Bytes.toBytes(joinColumn));
				Tuple2<String, Result> result=new Tuple2<String, Result>(Bytes.toString(key), t._2);
				
				return result;
			}
		});

		JavaPairRDD<String, Tuple2<Result, Result>> join = rddByJoinAttr.join(((DistributedResultSet) this.getRightInputResult()).getResultRDD());
		JavaPairRDD<String,Result> result=join.mapToPair(new PairFunction<Tuple2<String,Tuple2<Result,Result>>, String, Result>() {


			private static final long serialVersionUID = 7409628422156211523L;

			@Override
			public Tuple2<String, Result> call(Tuple2<String, Tuple2<Result, Result>> t) throws Exception {
				Tuple2<String, Result> subject=null;
				if(returnV.equals(returnValue.left)){
					subject=new Tuple2<String, Result>(t._1, t._2._1);
				}{
					subject=new Tuple2<String, Result>(t._1, t._2._2);
				}
				return subject;
			}
		});
		long end =System.currentTimeMillis();
		System.out.println("count : "+result.count());
		System.out.println("join time : "+(end-start));
		DistributedResultSet resultJoin = new DistributedResultSet();
		resultJoin.setResultRDD(result);
		return resultJoin;
	}
	public String getJoinColumn() {
		return joinColumn;
	}


	public void setJoinColumn(String joinColumn) {
		this.joinColumn = joinColumn;
	}

	public returnValue getReturnV() {
		return returnV;
	}

	public void setReturnV(returnValue returnV) {
		this.returnV = returnV;
	}
}
