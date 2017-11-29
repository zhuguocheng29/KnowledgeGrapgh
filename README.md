此版本为项目版本

KnowledgeGraph storage based on HBase0.98 项目主要使用HBase0.98来存储知识图谱的RDF三元组 在HBase中通过预分区将数据分组，使用了协处理器建二级索引，还实现了手工split操作，查询支持单个条件查询，最多2个连接查询，关键字查询，模糊查询等。 

项目主要层次架构如下： 
1.Sparql语句 
2.Sparql语句解析接口 
3.HBase
