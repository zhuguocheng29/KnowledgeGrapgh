package com.cetc28.seu.fromAntlr;

// Generated from Sparql.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SparqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, INT=3, BASE=4, PREFIX=5, MODIFY=6, DELETE=7, INSERT=8, 
		DATA=9, INTO=10, LOAD=11, CLEAR=12, CREATE=13, SILENT=14, DROP=15, EXISTS=16, 
		UNSAID=17, NOT=18, SELECT=19, DISTINCT=20, REDUCED=21, CONSTRUCT=22, DESCRIBE=23, 
		ASK=24, FROM=25, NAMED=26, WHERE=27, ORDER=28, GROUP=29, HAVING=30, BY=31, 
		ASC=32, DESC=33, LIMIT=34, OFFSET=35, OPTIONAL=36, GRAPH=37, UNION=38, 
		FILTER=39, A=40, AS=41, STR=42, LANG=43, LANGMATCHES=44, DATATYPE=45, 
		BOUND=46, SAMETERM=47, ISIRI=48, ISURI=49, ISBLANK=50, ISLITERAL=51, REGEX=52, 
		COUNT=53, SUM=54, MIN=55, MAX=56, AVG=57, TRUE=58, FALSE=59, IF=60, COALESCE=61, 
		IRI_REF=62, PNAME_NS=63, PNAME_LN=64, VAR1=65, VAR2=66, LANGTAG=67, INTEGER=68, 
		DECIMAL=69, DOUBLE=70, INTEGER_POSITIVE=71, DECIMAL_POSITIVE=72, DOUBLE_POSITIVE=73, 
		INTEGER_NEGATIVE=74, DECIMAL_NEGATIVE=75, DOUBLE_NEGATIVE=76, STRING_LITERAL1=77, 
		STRING_LITERAL2=78, STRING_LITERAL_LONG1=79, STRING_LITERAL_LONG2=80, 
		NIL=81, WS=82, ANON=83, BLANK_NODE_LABEL=84, REFERENCE=85, AND=86, OR=87, 
		OPEN_CURLY_BRACE=88, CLOSE_CURLY_BRACE=89, SEMICOLON=90, DOT=91, PLUS=92, 
		MINUS=93, ASTERISK=94, COMMA=95, NOT_SIGN=96, DIVIDE=97, EQUAL=98, LESS=99, 
		GREATER=100, OPEN_BRACE=101, CLOSE_BRACE=102, LESS_EQUAL=103, GREATER_EQUAL=104, 
		NOT_EQUAL=105, OPEN_SQUARE_BRACE=106, CLOSE_SQUARE_BRACE=107;
	public static final int
		RULE_query = 0, RULE_prologue = 1, RULE_baseDecl = 2, RULE_prefixDecl = 3, 
		RULE_update = 4, RULE_modify = 5, RULE_delete = 6, RULE_deleteData = 7, 
		RULE_deleteTemplate = 8, RULE_insert = 9, RULE_insertData = 10, RULE_insertTemplate = 11, 
		RULE_graphIri = 12, RULE_load = 13, RULE_clear = 14, RULE_manage = 15, 
		RULE_create = 16, RULE_drop = 17, RULE_updatePattern = 18, RULE_selectQuery = 19, 
		RULE_constructQuery = 20, RULE_describeQuery = 21, RULE_askQuery = 22, 
		RULE_datasetClause = 23, RULE_defaultGraphClause = 24, RULE_namedGraphClause = 25, 
		RULE_sourceSelector = 26, RULE_whereClause = 27, RULE_solutionModifier = 28, 
		RULE_groupClause = 29, RULE_groupCondition = 30, RULE_havingClause = 31, 
		RULE_havingCondition = 32, RULE_limitOffsetClauses = 33, RULE_orderClause = 34, 
		RULE_orderCondition = 35, RULE_limitClause = 36, RULE_offsetClause = 37, 
		RULE_groupGraphPattern = 38, RULE_groupGraphPatternSub = 39, RULE_project = 40, 
		RULE_triplesBlock = 41, RULE_graphPatternNotTriples = 42, RULE_optionalGraphPattern = 43, 
		RULE_graphGraphPattern = 44, RULE_existsElt = 45, RULE_notExistsElt = 46, 
		RULE_groupOrUnionGraphPattern = 47, RULE_filter = 48, RULE_constraint = 49, 
		RULE_functionCall = 50, RULE_argList = 51, RULE_constructTemplate = 52, 
		RULE_constructTriples = 53, RULE_triplesSameSubject = 54, RULE_propertyListNotEmpty = 55, 
		RULE_propertyList = 56, RULE_objectList = 57, RULE_object = 58, RULE_verb = 59, 
		RULE_triplesSameSubjectPath = 60, RULE_propertyListNotEmptyPath = 61, 
		RULE_propertyListPath = 62, RULE_verbPath = 63, RULE_verbSimple = 64, 
		RULE_pathUnit = 65, RULE_path = 66, RULE_pathAlternative = 67, RULE_pathSequence = 68, 
		RULE_pathElt = 69, RULE_pathEltOrReverse = 70, RULE_pathMod = 71, RULE_pathPrimary = 72, 
		RULE_triplesNode = 73, RULE_blankNodePropertyList = 74, RULE_collection = 75, 
		RULE_graphNode = 76, RULE_varOrTerm = 77, RULE_varOrIRIref = 78, RULE_variable = 79, 
		RULE_graphTerm = 80, RULE_expression = 81, RULE_conditionalOrExpression = 82, 
		RULE_conditionalAndExpression = 83, RULE_valueLogical = 84, RULE_relationalExpression = 85, 
		RULE_numericExpression = 86, RULE_additiveExpression = 87, RULE_multiplicativeExpression = 88, 
		RULE_unaryExpression = 89, RULE_primaryExpression = 90, RULE_brackettedExpression = 91, 
		RULE_builtInCall = 92, RULE_subSelect = 93, RULE_regexExpression = 94, 
		RULE_existsFunc = 95, RULE_notExistsFunc = 96, RULE_aggregate = 97, RULE_iriRefOrFunction = 98, 
		RULE_rdfLiteral = 99, RULE_numericLiteral = 100, RULE_numericLiteralUnsigned = 101, 
		RULE_numericLiteralPositive = 102, RULE_numericLiteralNegative = 103, 
		RULE_booleanLiteral = 104, RULE_string = 105, RULE_iriRef = 106, RULE_prefixedName = 107, 
		RULE_blankNode = 108;
	public static final String[] ruleNames = {
		"query", "prologue", "baseDecl", "prefixDecl", "update", "modify", "delete", 
		"deleteData", "deleteTemplate", "insert", "insertData", "insertTemplate", 
		"graphIri", "load", "clear", "manage", "create", "drop", "updatePattern", 
		"selectQuery", "constructQuery", "describeQuery", "askQuery", "datasetClause", 
		"defaultGraphClause", "namedGraphClause", "sourceSelector", "whereClause", 
		"solutionModifier", "groupClause", "groupCondition", "havingClause", "havingCondition", 
		"limitOffsetClauses", "orderClause", "orderCondition", "limitClause", 
		"offsetClause", "groupGraphPattern", "groupGraphPatternSub", "project", 
		"triplesBlock", "graphPatternNotTriples", "optionalGraphPattern", "graphGraphPattern", 
		"existsElt", "notExistsElt", "groupOrUnionGraphPattern", "filter", "constraint", 
		"functionCall", "argList", "constructTemplate", "constructTriples", "triplesSameSubject", 
		"propertyListNotEmpty", "propertyList", "objectList", "object", "verb", 
		"triplesSameSubjectPath", "propertyListNotEmptyPath", "propertyListPath", 
		"verbPath", "verbSimple", "pathUnit", "path", "pathAlternative", "pathSequence", 
		"pathElt", "pathEltOrReverse", "pathMod", "pathPrimary", "triplesNode", 
		"blankNodePropertyList", "collection", "graphNode", "varOrTerm", "varOrIRIref", 
		"variable", "graphTerm", "expression", "conditionalOrExpression", "conditionalAndExpression", 
		"valueLogical", "relationalExpression", "numericExpression", "additiveExpression", 
		"multiplicativeExpression", "unaryExpression", "primaryExpression", "brackettedExpression", 
		"builtInCall", "subSelect", "regexExpression", "existsFunc", "notExistsFunc", 
		"aggregate", "iriRefOrFunction", "rdfLiteral", "numericLiteral", "numericLiteralUnsigned", 
		"numericLiteralPositive", "numericLiteralNegative", "booleanLiteral", 
		"string", "iriRef", "prefixedName", "blankNode"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'^'", "'?'", null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "'^^'", "'&&'", "'||'", "'{'", "'}'", "';'", "'.'", "'+'", "'-'", 
		"'*'", "','", "'!'", "'/'", "'='", "'<'", "'>'", "'('", "')'", "'<='", 
		"'>='", "'!='", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "INT", "BASE", "PREFIX", "MODIFY", "DELETE", "INSERT", 
		"DATA", "INTO", "LOAD", "CLEAR", "CREATE", "SILENT", "DROP", "EXISTS", 
		"UNSAID", "NOT", "SELECT", "DISTINCT", "REDUCED", "CONSTRUCT", "DESCRIBE", 
		"ASK", "FROM", "NAMED", "WHERE", "ORDER", "GROUP", "HAVING", "BY", "ASC", 
		"DESC", "LIMIT", "OFFSET", "OPTIONAL", "GRAPH", "UNION", "FILTER", "A", 
		"AS", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "SAMETERM", "ISIRI", 
		"ISURI", "ISBLANK", "ISLITERAL", "REGEX", "COUNT", "SUM", "MIN", "MAX", 
		"AVG", "TRUE", "FALSE", "IF", "COALESCE", "IRI_REF", "PNAME_NS", "PNAME_LN", 
		"VAR1", "VAR2", "LANGTAG", "INTEGER", "DECIMAL", "DOUBLE", "INTEGER_POSITIVE", 
		"DECIMAL_POSITIVE", "DOUBLE_POSITIVE", "INTEGER_NEGATIVE", "DECIMAL_NEGATIVE", 
		"DOUBLE_NEGATIVE", "STRING_LITERAL1", "STRING_LITERAL2", "STRING_LITERAL_LONG1", 
		"STRING_LITERAL_LONG2", "NIL", "WS", "ANON", "BLANK_NODE_LABEL", "REFERENCE", 
		"AND", "OR", "OPEN_CURLY_BRACE", "CLOSE_CURLY_BRACE", "SEMICOLON", "DOT", 
		"PLUS", "MINUS", "ASTERISK", "COMMA", "NOT_SIGN", "DIVIDE", "EQUAL", "LESS", 
		"GREATER", "OPEN_BRACE", "CLOSE_BRACE", "LESS_EQUAL", "GREATER_EQUAL", 
		"NOT_EQUAL", "OPEN_SQUARE_BRACE", "CLOSE_SQUARE_BRACE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Sparql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SparqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public PrologueContext prologue() {
			return getRuleContext(PrologueContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SparqlParser.EOF, 0); }
		public SelectQueryContext selectQuery() {
			return getRuleContext(SelectQueryContext.class,0);
		}
		public ConstructQueryContext constructQuery() {
			return getRuleContext(ConstructQueryContext.class,0);
		}
		public DescribeQueryContext describeQuery() {
			return getRuleContext(DescribeQueryContext.class,0);
		}
		public AskQueryContext askQuery() {
			return getRuleContext(AskQueryContext.class,0);
		}
		public UpdateContext update() {
			return getRuleContext(UpdateContext.class,0);
		}
		public ManageContext manage() {
			return getRuleContext(ManageContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			prologue();
			setState(225);
			switch (_input.LA(1)) {
			case SELECT:
				{
				setState(219);
				selectQuery();
				}
				break;
			case CONSTRUCT:
				{
				setState(220);
				constructQuery();
				}
				break;
			case DESCRIBE:
				{
				setState(221);
				describeQuery();
				}
				break;
			case ASK:
				{
				setState(222);
				askQuery();
				}
				break;
			case MODIFY:
			case DELETE:
			case INSERT:
			case LOAD:
			case CLEAR:
				{
				setState(223);
				update();
				}
				break;
			case CREATE:
			case DROP:
				{
				setState(224);
				manage();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(227);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrologueContext extends ParserRuleContext {
		public BaseDeclContext baseDecl() {
			return getRuleContext(BaseDeclContext.class,0);
		}
		public List<PrefixDeclContext> prefixDecl() {
			return getRuleContexts(PrefixDeclContext.class);
		}
		public PrefixDeclContext prefixDecl(int i) {
			return getRuleContext(PrefixDeclContext.class,i);
		}
		public PrologueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prologue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPrologue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPrologue(this);
		}
	}

	public final PrologueContext prologue() throws RecognitionException {
		PrologueContext _localctx = new PrologueContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_prologue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_la = _input.LA(1);
			if (_la==BASE) {
				{
				setState(229);
				baseDecl();
				}
			}

			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PREFIX) {
				{
				{
				setState(232);
				prefixDecl();
				}
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseDeclContext extends ParserRuleContext {
		public TerminalNode BASE() { return getToken(SparqlParser.BASE, 0); }
		public TerminalNode IRI_REF() { return getToken(SparqlParser.IRI_REF, 0); }
		public BaseDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterBaseDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitBaseDecl(this);
		}
	}

	public final BaseDeclContext baseDecl() throws RecognitionException {
		BaseDeclContext _localctx = new BaseDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_baseDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(BASE);
			setState(239);
			match(IRI_REF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrefixDeclContext extends ParserRuleContext {
		public TerminalNode PREFIX() { return getToken(SparqlParser.PREFIX, 0); }
		public TerminalNode PNAME_NS() { return getToken(SparqlParser.PNAME_NS, 0); }
		public TerminalNode IRI_REF() { return getToken(SparqlParser.IRI_REF, 0); }
		public PrefixDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPrefixDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPrefixDecl(this);
		}
	}

	public final PrefixDeclContext prefixDecl() throws RecognitionException {
		PrefixDeclContext _localctx = new PrefixDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_prefixDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(PREFIX);
			setState(242);
			match(PNAME_NS);
			setState(243);
			match(IRI_REF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateContext extends ParserRuleContext {
		public ModifyContext modify() {
			return getRuleContext(ModifyContext.class,0);
		}
		public InsertContext insert() {
			return getRuleContext(InsertContext.class,0);
		}
		public DeleteContext delete() {
			return getRuleContext(DeleteContext.class,0);
		}
		public LoadContext load() {
			return getRuleContext(LoadContext.class,0);
		}
		public ClearContext clear() {
			return getRuleContext(ClearContext.class,0);
		}
		public UpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitUpdate(this);
		}
	}

	public final UpdateContext update() throws RecognitionException {
		UpdateContext _localctx = new UpdateContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_update);
		try {
			setState(250);
			switch (_input.LA(1)) {
			case MODIFY:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				modify();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				insert();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(247);
				delete();
				}
				break;
			case LOAD:
				enterOuterAlt(_localctx, 4);
				{
				setState(248);
				load();
				}
				break;
			case CLEAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(249);
				clear();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModifyContext extends ParserRuleContext {
		public TerminalNode MODIFY() { return getToken(SparqlParser.MODIFY, 0); }
		public TerminalNode DELETE() { return getToken(SparqlParser.DELETE, 0); }
		public List<ConstructTemplateContext> constructTemplate() {
			return getRuleContexts(ConstructTemplateContext.class);
		}
		public ConstructTemplateContext constructTemplate(int i) {
			return getRuleContext(ConstructTemplateContext.class,i);
		}
		public TerminalNode INSERT() { return getToken(SparqlParser.INSERT, 0); }
		public List<GraphIriContext> graphIri() {
			return getRuleContexts(GraphIriContext.class);
		}
		public GraphIriContext graphIri(int i) {
			return getRuleContext(GraphIriContext.class,i);
		}
		public UpdatePatternContext updatePattern() {
			return getRuleContext(UpdatePatternContext.class,0);
		}
		public ModifyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modify; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterModify(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitModify(this);
		}
	}

	public final ModifyContext modify() throws RecognitionException {
		ModifyContext _localctx = new ModifyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_modify);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(MODIFY);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 37)) & ~0x3f) == 0 && ((1L << (_la - 37)) & ((1L << (GRAPH - 37)) | (1L << (IRI_REF - 37)) | (1L << (PNAME_NS - 37)) | (1L << (PNAME_LN - 37)))) != 0)) {
				{
				{
				setState(253);
				graphIri();
				}
				}
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(259);
			match(DELETE);
			setState(260);
			constructTemplate();
			setState(261);
			match(INSERT);
			setState(262);
			constructTemplate();
			setState(264);
			_la = _input.LA(1);
			if (_la==WHERE || _la==OPEN_CURLY_BRACE) {
				{
				setState(263);
				updatePattern();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeleteContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(SparqlParser.DELETE, 0); }
		public DeleteDataContext deleteData() {
			return getRuleContext(DeleteDataContext.class,0);
		}
		public DeleteTemplateContext deleteTemplate() {
			return getRuleContext(DeleteTemplateContext.class,0);
		}
		public DeleteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterDelete(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitDelete(this);
		}
	}

	public final DeleteContext delete() throws RecognitionException {
		DeleteContext _localctx = new DeleteContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_delete);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(DELETE);
			setState(269);
			switch (_input.LA(1)) {
			case DATA:
				{
				setState(267);
				deleteData();
				}
				break;
			case FROM:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case OPEN_CURLY_BRACE:
				{
				setState(268);
				deleteTemplate();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeleteDataContext extends ParserRuleContext {
		public TerminalNode DATA() { return getToken(SparqlParser.DATA, 0); }
		public ConstructTemplateContext constructTemplate() {
			return getRuleContext(ConstructTemplateContext.class,0);
		}
		public List<IriRefContext> iriRef() {
			return getRuleContexts(IriRefContext.class);
		}
		public IriRefContext iriRef(int i) {
			return getRuleContext(IriRefContext.class,i);
		}
		public List<TerminalNode> FROM() { return getTokens(SparqlParser.FROM); }
		public TerminalNode FROM(int i) {
			return getToken(SparqlParser.FROM, i);
		}
		public DeleteDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterDeleteData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitDeleteData(this);
		}
	}

	public final DeleteDataContext deleteData() throws RecognitionException {
		DeleteDataContext _localctx = new DeleteDataContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_deleteData);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			match(DATA);
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 25)) & ~0x3f) == 0 && ((1L << (_la - 25)) & ((1L << (FROM - 25)) | (1L << (IRI_REF - 25)) | (1L << (PNAME_NS - 25)) | (1L << (PNAME_LN - 25)))) != 0)) {
				{
				{
				setState(273);
				_la = _input.LA(1);
				if (_la==FROM) {
					{
					setState(272);
					match(FROM);
					}
				}

				setState(275);
				iriRef();
				}
				}
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(281);
			constructTemplate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeleteTemplateContext extends ParserRuleContext {
		public ConstructTemplateContext constructTemplate() {
			return getRuleContext(ConstructTemplateContext.class,0);
		}
		public List<IriRefContext> iriRef() {
			return getRuleContexts(IriRefContext.class);
		}
		public IriRefContext iriRef(int i) {
			return getRuleContext(IriRefContext.class,i);
		}
		public UpdatePatternContext updatePattern() {
			return getRuleContext(UpdatePatternContext.class,0);
		}
		public List<TerminalNode> FROM() { return getTokens(SparqlParser.FROM); }
		public TerminalNode FROM(int i) {
			return getToken(SparqlParser.FROM, i);
		}
		public DeleteTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteTemplate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterDeleteTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitDeleteTemplate(this);
		}
	}

	public final DeleteTemplateContext deleteTemplate() throws RecognitionException {
		DeleteTemplateContext _localctx = new DeleteTemplateContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_deleteTemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 25)) & ~0x3f) == 0 && ((1L << (_la - 25)) & ((1L << (FROM - 25)) | (1L << (IRI_REF - 25)) | (1L << (PNAME_NS - 25)) | (1L << (PNAME_LN - 25)))) != 0)) {
				{
				{
				setState(284);
				_la = _input.LA(1);
				if (_la==FROM) {
					{
					setState(283);
					match(FROM);
					}
				}

				setState(286);
				iriRef();
				}
				}
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(292);
			constructTemplate();
			setState(294);
			_la = _input.LA(1);
			if (_la==WHERE || _la==OPEN_CURLY_BRACE) {
				{
				setState(293);
				updatePattern();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InsertContext extends ParserRuleContext {
		public TerminalNode INSERT() { return getToken(SparqlParser.INSERT, 0); }
		public InsertDataContext insertData() {
			return getRuleContext(InsertDataContext.class,0);
		}
		public InsertTemplateContext insertTemplate() {
			return getRuleContext(InsertTemplateContext.class,0);
		}
		public InsertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterInsert(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitInsert(this);
		}
	}

	public final InsertContext insert() throws RecognitionException {
		InsertContext _localctx = new InsertContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_insert);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(INSERT);
			setState(299);
			switch (_input.LA(1)) {
			case DATA:
				{
				setState(297);
				insertData();
				}
				break;
			case INTO:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case OPEN_CURLY_BRACE:
				{
				setState(298);
				insertTemplate();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InsertDataContext extends ParserRuleContext {
		public TerminalNode DATA() { return getToken(SparqlParser.DATA, 0); }
		public ConstructTemplateContext constructTemplate() {
			return getRuleContext(ConstructTemplateContext.class,0);
		}
		public List<IriRefContext> iriRef() {
			return getRuleContexts(IriRefContext.class);
		}
		public IriRefContext iriRef(int i) {
			return getRuleContext(IriRefContext.class,i);
		}
		public List<TerminalNode> INTO() { return getTokens(SparqlParser.INTO); }
		public TerminalNode INTO(int i) {
			return getToken(SparqlParser.INTO, i);
		}
		public InsertDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterInsertData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitInsertData(this);
		}
	}

	public final InsertDataContext insertData() throws RecognitionException {
		InsertDataContext _localctx = new InsertDataContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_insertData);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			match(DATA);
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (INTO - 10)) | (1L << (IRI_REF - 10)) | (1L << (PNAME_NS - 10)) | (1L << (PNAME_LN - 10)))) != 0)) {
				{
				{
				setState(303);
				_la = _input.LA(1);
				if (_la==INTO) {
					{
					setState(302);
					match(INTO);
					}
				}

				setState(305);
				iriRef();
				}
				}
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(311);
			constructTemplate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InsertTemplateContext extends ParserRuleContext {
		public ConstructTemplateContext constructTemplate() {
			return getRuleContext(ConstructTemplateContext.class,0);
		}
		public List<IriRefContext> iriRef() {
			return getRuleContexts(IriRefContext.class);
		}
		public IriRefContext iriRef(int i) {
			return getRuleContext(IriRefContext.class,i);
		}
		public UpdatePatternContext updatePattern() {
			return getRuleContext(UpdatePatternContext.class,0);
		}
		public List<TerminalNode> INTO() { return getTokens(SparqlParser.INTO); }
		public TerminalNode INTO(int i) {
			return getToken(SparqlParser.INTO, i);
		}
		public InsertTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertTemplate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterInsertTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitInsertTemplate(this);
		}
	}

	public final InsertTemplateContext insertTemplate() throws RecognitionException {
		InsertTemplateContext _localctx = new InsertTemplateContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_insertTemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (INTO - 10)) | (1L << (IRI_REF - 10)) | (1L << (PNAME_NS - 10)) | (1L << (PNAME_LN - 10)))) != 0)) {
				{
				{
				setState(314);
				_la = _input.LA(1);
				if (_la==INTO) {
					{
					setState(313);
					match(INTO);
					}
				}

				setState(316);
				iriRef();
				}
				}
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(322);
			constructTemplate();
			setState(324);
			_la = _input.LA(1);
			if (_la==WHERE || _la==OPEN_CURLY_BRACE) {
				{
				setState(323);
				updatePattern();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphIriContext extends ParserRuleContext {
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public TerminalNode GRAPH() { return getToken(SparqlParser.GRAPH, 0); }
		public GraphIriContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphIri; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGraphIri(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGraphIri(this);
		}
	}

	public final GraphIriContext graphIri() throws RecognitionException {
		GraphIriContext _localctx = new GraphIriContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_graphIri);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			_la = _input.LA(1);
			if (_la==GRAPH) {
				{
				setState(326);
				match(GRAPH);
				}
			}

			setState(329);
			iriRef();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoadContext extends ParserRuleContext {
		public TerminalNode LOAD() { return getToken(SparqlParser.LOAD, 0); }
		public List<IriRefContext> iriRef() {
			return getRuleContexts(IriRefContext.class);
		}
		public IriRefContext iriRef(int i) {
			return getRuleContext(IriRefContext.class,i);
		}
		public TerminalNode INTO() { return getToken(SparqlParser.INTO, 0); }
		public LoadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_load; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterLoad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitLoad(this);
		}
	}

	public final LoadContext load() throws RecognitionException {
		LoadContext _localctx = new LoadContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_load);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(LOAD);
			setState(333); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(332);
				iriRef();
				}
				}
				setState(335); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (IRI_REF - 62)) | (1L << (PNAME_NS - 62)) | (1L << (PNAME_LN - 62)))) != 0) );
			setState(339);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(337);
				match(INTO);
				setState(338);
				iriRef();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClearContext extends ParserRuleContext {
		public TerminalNode CLEAR() { return getToken(SparqlParser.CLEAR, 0); }
		public GraphIriContext graphIri() {
			return getRuleContext(GraphIriContext.class,0);
		}
		public ClearContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clear; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterClear(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitClear(this);
		}
	}

	public final ClearContext clear() throws RecognitionException {
		ClearContext _localctx = new ClearContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_clear);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			match(CLEAR);
			setState(343);
			_la = _input.LA(1);
			if (((((_la - 37)) & ~0x3f) == 0 && ((1L << (_la - 37)) & ((1L << (GRAPH - 37)) | (1L << (IRI_REF - 37)) | (1L << (PNAME_NS - 37)) | (1L << (PNAME_LN - 37)))) != 0)) {
				{
				setState(342);
				graphIri();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ManageContext extends ParserRuleContext {
		public CreateContext create() {
			return getRuleContext(CreateContext.class,0);
		}
		public DropContext drop() {
			return getRuleContext(DropContext.class,0);
		}
		public ManageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_manage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterManage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitManage(this);
		}
	}

	public final ManageContext manage() throws RecognitionException {
		ManageContext _localctx = new ManageContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_manage);
		try {
			setState(347);
			switch (_input.LA(1)) {
			case CREATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(345);
				create();
				}
				break;
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(346);
				drop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreateContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(SparqlParser.CREATE, 0); }
		public GraphIriContext graphIri() {
			return getRuleContext(GraphIriContext.class,0);
		}
		public TerminalNode SILENT() { return getToken(SparqlParser.SILENT, 0); }
		public CreateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterCreate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitCreate(this);
		}
	}

	public final CreateContext create() throws RecognitionException {
		CreateContext _localctx = new CreateContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_create);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			match(CREATE);
			setState(351);
			_la = _input.LA(1);
			if (_la==SILENT) {
				{
				setState(350);
				match(SILENT);
				}
			}

			setState(353);
			graphIri();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DropContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(SparqlParser.DROP, 0); }
		public GraphIriContext graphIri() {
			return getRuleContext(GraphIriContext.class,0);
		}
		public TerminalNode SILENT() { return getToken(SparqlParser.SILENT, 0); }
		public DropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterDrop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitDrop(this);
		}
	}

	public final DropContext drop() throws RecognitionException {
		DropContext _localctx = new DropContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_drop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(DROP);
			setState(357);
			_la = _input.LA(1);
			if (_la==SILENT) {
				{
				setState(356);
				match(SILENT);
				}
			}

			setState(359);
			graphIri();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdatePatternContext extends ParserRuleContext {
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(SparqlParser.WHERE, 0); }
		public UpdatePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updatePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterUpdatePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitUpdatePattern(this);
		}
	}

	public final UpdatePatternContext updatePattern() throws RecognitionException {
		UpdatePatternContext _localctx = new UpdatePatternContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_updatePattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(361);
				match(WHERE);
				}
			}

			setState(364);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectQueryContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(SparqlParser.SELECT, 0); }
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public SolutionModifierContext solutionModifier() {
			return getRuleContext(SolutionModifierContext.class,0);
		}
		public TerminalNode ASTERISK() { return getToken(SparqlParser.ASTERISK, 0); }
		public List<DatasetClauseContext> datasetClause() {
			return getRuleContexts(DatasetClauseContext.class);
		}
		public DatasetClauseContext datasetClause(int i) {
			return getRuleContext(DatasetClauseContext.class,i);
		}
		public TerminalNode DISTINCT() { return getToken(SparqlParser.DISTINCT, 0); }
		public TerminalNode REDUCED() { return getToken(SparqlParser.REDUCED, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public SelectQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterSelectQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitSelectQuery(this);
		}
	}

	public final SelectQueryContext selectQuery() throws RecognitionException {
		SelectQueryContext _localctx = new SelectQueryContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_selectQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(SELECT);
			setState(368);
			_la = _input.LA(1);
			if (_la==DISTINCT || _la==REDUCED) {
				{
				setState(367);
				_la = _input.LA(1);
				if ( !(_la==DISTINCT || _la==REDUCED) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(376);
			switch (_input.LA(1)) {
			case VAR1:
			case VAR2:
				{
				setState(371); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(370);
					variable();
					}
					}
					setState(373); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR1 || _la==VAR2 );
				}
				break;
			case ASTERISK:
				{
				setState(375);
				match(ASTERISK);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(381);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FROM) {
				{
				{
				setState(378);
				datasetClause();
				}
				}
				setState(383);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(384);
			whereClause();
			setState(385);
			solutionModifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructQueryContext extends ParserRuleContext {
		public TerminalNode CONSTRUCT() { return getToken(SparqlParser.CONSTRUCT, 0); }
		public ConstructTemplateContext constructTemplate() {
			return getRuleContext(ConstructTemplateContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public SolutionModifierContext solutionModifier() {
			return getRuleContext(SolutionModifierContext.class,0);
		}
		public List<DatasetClauseContext> datasetClause() {
			return getRuleContexts(DatasetClauseContext.class);
		}
		public DatasetClauseContext datasetClause(int i) {
			return getRuleContext(DatasetClauseContext.class,i);
		}
		public ConstructQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterConstructQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitConstructQuery(this);
		}
	}

	public final ConstructQueryContext constructQuery() throws RecognitionException {
		ConstructQueryContext _localctx = new ConstructQueryContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_constructQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(CONSTRUCT);
			setState(388);
			constructTemplate();
			setState(392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FROM) {
				{
				{
				setState(389);
				datasetClause();
				}
				}
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(395);
			whereClause();
			setState(396);
			solutionModifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DescribeQueryContext extends ParserRuleContext {
		public TerminalNode DESCRIBE() { return getToken(SparqlParser.DESCRIBE, 0); }
		public SolutionModifierContext solutionModifier() {
			return getRuleContext(SolutionModifierContext.class,0);
		}
		public TerminalNode ASTERISK() { return getToken(SparqlParser.ASTERISK, 0); }
		public List<DatasetClauseContext> datasetClause() {
			return getRuleContexts(DatasetClauseContext.class);
		}
		public DatasetClauseContext datasetClause(int i) {
			return getRuleContext(DatasetClauseContext.class,i);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public List<VarOrIRIrefContext> varOrIRIref() {
			return getRuleContexts(VarOrIRIrefContext.class);
		}
		public VarOrIRIrefContext varOrIRIref(int i) {
			return getRuleContext(VarOrIRIrefContext.class,i);
		}
		public DescribeQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_describeQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterDescribeQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitDescribeQuery(this);
		}
	}

	public final DescribeQueryContext describeQuery() throws RecognitionException {
		DescribeQueryContext _localctx = new DescribeQueryContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_describeQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(DESCRIBE);
			setState(405);
			switch (_input.LA(1)) {
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
				{
				setState(400); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(399);
					varOrIRIref();
					}
					}
					setState(402); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (IRI_REF - 62)) | (1L << (PNAME_NS - 62)) | (1L << (PNAME_LN - 62)) | (1L << (VAR1 - 62)) | (1L << (VAR2 - 62)))) != 0) );
				}
				break;
			case ASTERISK:
				{
				setState(404);
				match(ASTERISK);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FROM) {
				{
				{
				setState(407);
				datasetClause();
				}
				}
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(414);
			_la = _input.LA(1);
			if (_la==WHERE || _la==OPEN_CURLY_BRACE) {
				{
				setState(413);
				whereClause();
				}
			}

			setState(416);
			solutionModifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AskQueryContext extends ParserRuleContext {
		public TerminalNode ASK() { return getToken(SparqlParser.ASK, 0); }
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public List<DatasetClauseContext> datasetClause() {
			return getRuleContexts(DatasetClauseContext.class);
		}
		public DatasetClauseContext datasetClause(int i) {
			return getRuleContext(DatasetClauseContext.class,i);
		}
		public AskQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_askQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterAskQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitAskQuery(this);
		}
	}

	public final AskQueryContext askQuery() throws RecognitionException {
		AskQueryContext _localctx = new AskQueryContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_askQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			match(ASK);
			setState(422);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FROM) {
				{
				{
				setState(419);
				datasetClause();
				}
				}
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(425);
			whereClause();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DatasetClauseContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(SparqlParser.FROM, 0); }
		public DefaultGraphClauseContext defaultGraphClause() {
			return getRuleContext(DefaultGraphClauseContext.class,0);
		}
		public NamedGraphClauseContext namedGraphClause() {
			return getRuleContext(NamedGraphClauseContext.class,0);
		}
		public DatasetClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_datasetClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterDatasetClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitDatasetClause(this);
		}
	}

	public final DatasetClauseContext datasetClause() throws RecognitionException {
		DatasetClauseContext _localctx = new DatasetClauseContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_datasetClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			match(FROM);
			setState(430);
			switch (_input.LA(1)) {
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
				{
				setState(428);
				defaultGraphClause();
				}
				break;
			case NAMED:
				{
				setState(429);
				namedGraphClause();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultGraphClauseContext extends ParserRuleContext {
		public SourceSelectorContext sourceSelector() {
			return getRuleContext(SourceSelectorContext.class,0);
		}
		public DefaultGraphClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultGraphClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterDefaultGraphClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitDefaultGraphClause(this);
		}
	}

	public final DefaultGraphClauseContext defaultGraphClause() throws RecognitionException {
		DefaultGraphClauseContext _localctx = new DefaultGraphClauseContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_defaultGraphClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432);
			sourceSelector();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamedGraphClauseContext extends ParserRuleContext {
		public TerminalNode NAMED() { return getToken(SparqlParser.NAMED, 0); }
		public SourceSelectorContext sourceSelector() {
			return getRuleContext(SourceSelectorContext.class,0);
		}
		public NamedGraphClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedGraphClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterNamedGraphClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitNamedGraphClause(this);
		}
	}

	public final NamedGraphClauseContext namedGraphClause() throws RecognitionException {
		NamedGraphClauseContext _localctx = new NamedGraphClauseContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_namedGraphClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			match(NAMED);
			setState(435);
			sourceSelector();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SourceSelectorContext extends ParserRuleContext {
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public SourceSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterSourceSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitSourceSelector(this);
		}
	}

	public final SourceSelectorContext sourceSelector() throws RecognitionException {
		SourceSelectorContext _localctx = new SourceSelectorContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_sourceSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			iriRef();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereClauseContext extends ParserRuleContext {
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(SparqlParser.WHERE, 0); }
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitWhereClause(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_whereClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(439);
				match(WHERE);
				}
			}

			setState(442);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SolutionModifierContext extends ParserRuleContext {
		public GroupClauseContext groupClause() {
			return getRuleContext(GroupClauseContext.class,0);
		}
		public HavingClauseContext havingClause() {
			return getRuleContext(HavingClauseContext.class,0);
		}
		public OrderClauseContext orderClause() {
			return getRuleContext(OrderClauseContext.class,0);
		}
		public LimitOffsetClausesContext limitOffsetClauses() {
			return getRuleContext(LimitOffsetClausesContext.class,0);
		}
		public SolutionModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_solutionModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterSolutionModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitSolutionModifier(this);
		}
	}

	public final SolutionModifierContext solutionModifier() throws RecognitionException {
		SolutionModifierContext _localctx = new SolutionModifierContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_solutionModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(444);
				groupClause();
				}
			}

			setState(448);
			_la = _input.LA(1);
			if (_la==HAVING) {
				{
				setState(447);
				havingClause();
				}
			}

			setState(451);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(450);
				orderClause();
				}
			}

			setState(454);
			_la = _input.LA(1);
			if (_la==LIMIT || _la==OFFSET) {
				{
				setState(453);
				limitOffsetClauses();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupClauseContext extends ParserRuleContext {
		public TerminalNode GROUP() { return getToken(SparqlParser.GROUP, 0); }
		public TerminalNode BY() { return getToken(SparqlParser.BY, 0); }
		public List<GroupConditionContext> groupCondition() {
			return getRuleContexts(GroupConditionContext.class);
		}
		public GroupConditionContext groupCondition(int i) {
			return getRuleContext(GroupConditionContext.class,i);
		}
		public GroupClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGroupClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGroupClause(this);
		}
	}

	public final GroupClauseContext groupClause() throws RecognitionException {
		GroupClauseContext _localctx = new GroupClauseContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_groupClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(456);
			match(GROUP);
			setState(457);
			match(BY);
			setState(459); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(458);
				groupCondition();
				}
				}
				setState(461); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXISTS) | (1L << UNSAID) | (1L << NOT) | (1L << STR) | (1L << LANG) | (1L << LANGMATCHES) | (1L << DATATYPE) | (1L << BOUND) | (1L << SAMETERM) | (1L << ISIRI) | (1L << ISURI) | (1L << ISBLANK) | (1L << ISLITERAL) | (1L << REGEX) | (1L << IF) | (1L << COALESCE) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PNAME_LN - 64)) | (1L << (VAR1 - 64)) | (1L << (VAR2 - 64)) | (1L << (OPEN_BRACE - 64)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupConditionContext extends ParserRuleContext {
		public BuiltInCallContext builtInCall() {
			return getRuleContext(BuiltInCallContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(SparqlParser.OPEN_BRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(SparqlParser.CLOSE_BRACE, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode AS() { return getToken(SparqlParser.AS, 0); }
		public GroupConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGroupCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGroupCondition(this);
		}
	}

	public final GroupConditionContext groupCondition() throws RecognitionException {
		GroupConditionContext _localctx = new GroupConditionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_groupCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			switch (_input.LA(1)) {
			case EXISTS:
			case UNSAID:
			case NOT:
			case STR:
			case LANG:
			case LANGMATCHES:
			case DATATYPE:
			case BOUND:
			case SAMETERM:
			case ISIRI:
			case ISURI:
			case ISBLANK:
			case ISLITERAL:
			case REGEX:
			case IF:
			case COALESCE:
				{
				setState(463);
				builtInCall();
				}
				break;
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
				{
				setState(464);
				functionCall();
				}
				break;
			case OPEN_BRACE:
				{
				setState(465);
				match(OPEN_BRACE);
				setState(466);
				expression();
				setState(469);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(467);
					match(AS);
					setState(468);
					variable();
					}
				}

				setState(471);
				match(CLOSE_BRACE);
				}
				break;
			case VAR1:
			case VAR2:
				{
				setState(473);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HavingClauseContext extends ParserRuleContext {
		public TerminalNode HAVING() { return getToken(SparqlParser.HAVING, 0); }
		public List<HavingConditionContext> havingCondition() {
			return getRuleContexts(HavingConditionContext.class);
		}
		public HavingConditionContext havingCondition(int i) {
			return getRuleContext(HavingConditionContext.class,i);
		}
		public HavingClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_havingClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterHavingClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitHavingClause(this);
		}
	}

	public final HavingClauseContext havingClause() throws RecognitionException {
		HavingClauseContext _localctx = new HavingClauseContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_havingClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(476);
			match(HAVING);
			setState(478); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(477);
				havingCondition();
				}
				}
				setState(480); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXISTS) | (1L << UNSAID) | (1L << NOT) | (1L << STR) | (1L << LANG) | (1L << LANGMATCHES) | (1L << DATATYPE) | (1L << BOUND) | (1L << SAMETERM) | (1L << ISIRI) | (1L << ISURI) | (1L << ISBLANK) | (1L << ISLITERAL) | (1L << REGEX) | (1L << IF) | (1L << COALESCE) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || _la==PNAME_LN || _la==OPEN_BRACE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HavingConditionContext extends ParserRuleContext {
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public HavingConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_havingCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterHavingCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitHavingCondition(this);
		}
	}

	public final HavingConditionContext havingCondition() throws RecognitionException {
		HavingConditionContext _localctx = new HavingConditionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_havingCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			constraint();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LimitOffsetClausesContext extends ParserRuleContext {
		public LimitClauseContext limitClause() {
			return getRuleContext(LimitClauseContext.class,0);
		}
		public OffsetClauseContext offsetClause() {
			return getRuleContext(OffsetClauseContext.class,0);
		}
		public LimitOffsetClausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitOffsetClauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterLimitOffsetClauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitLimitOffsetClauses(this);
		}
	}

	public final LimitOffsetClausesContext limitOffsetClauses() throws RecognitionException {
		LimitOffsetClausesContext _localctx = new LimitOffsetClausesContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_limitOffsetClauses);
		int _la;
		try {
			setState(492);
			switch (_input.LA(1)) {
			case LIMIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(484);
				limitClause();
				setState(486);
				_la = _input.LA(1);
				if (_la==OFFSET) {
					{
					setState(485);
					offsetClause();
					}
				}

				}
				break;
			case OFFSET:
				enterOuterAlt(_localctx, 2);
				{
				setState(488);
				offsetClause();
				setState(490);
				_la = _input.LA(1);
				if (_la==LIMIT) {
					{
					setState(489);
					limitClause();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderClauseContext extends ParserRuleContext {
		public TerminalNode ORDER() { return getToken(SparqlParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(SparqlParser.BY, 0); }
		public List<OrderConditionContext> orderCondition() {
			return getRuleContexts(OrderConditionContext.class);
		}
		public OrderConditionContext orderCondition(int i) {
			return getRuleContext(OrderConditionContext.class,i);
		}
		public OrderClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterOrderClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitOrderClause(this);
		}
	}

	public final OrderClauseContext orderClause() throws RecognitionException {
		OrderClauseContext _localctx = new OrderClauseContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_orderClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			match(ORDER);
			setState(495);
			match(BY);
			setState(497); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(496);
				orderCondition();
				}
				}
				setState(499); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXISTS) | (1L << UNSAID) | (1L << NOT) | (1L << ASC) | (1L << DESC) | (1L << STR) | (1L << LANG) | (1L << LANGMATCHES) | (1L << DATATYPE) | (1L << BOUND) | (1L << SAMETERM) | (1L << ISIRI) | (1L << ISURI) | (1L << ISBLANK) | (1L << ISLITERAL) | (1L << REGEX) | (1L << IF) | (1L << COALESCE) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PNAME_LN - 64)) | (1L << (VAR1 - 64)) | (1L << (VAR2 - 64)) | (1L << (OPEN_BRACE - 64)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderConditionContext extends ParserRuleContext {
		public BrackettedExpressionContext brackettedExpression() {
			return getRuleContext(BrackettedExpressionContext.class,0);
		}
		public TerminalNode ASC() { return getToken(SparqlParser.ASC, 0); }
		public TerminalNode DESC() { return getToken(SparqlParser.DESC, 0); }
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public OrderConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterOrderCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitOrderCondition(this);
		}
	}

	public final OrderConditionContext orderCondition() throws RecognitionException {
		OrderConditionContext _localctx = new OrderConditionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_orderCondition);
		int _la;
		try {
			setState(507);
			switch (_input.LA(1)) {
			case ASC:
			case DESC:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(501);
				_la = _input.LA(1);
				if ( !(_la==ASC || _la==DESC) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(502);
				brackettedExpression();
				}
				}
				break;
			case EXISTS:
			case UNSAID:
			case NOT:
			case STR:
			case LANG:
			case LANGMATCHES:
			case DATATYPE:
			case BOUND:
			case SAMETERM:
			case ISIRI:
			case ISURI:
			case ISBLANK:
			case ISLITERAL:
			case REGEX:
			case IF:
			case COALESCE:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(505);
				switch (_input.LA(1)) {
				case EXISTS:
				case UNSAID:
				case NOT:
				case STR:
				case LANG:
				case LANGMATCHES:
				case DATATYPE:
				case BOUND:
				case SAMETERM:
				case ISIRI:
				case ISURI:
				case ISBLANK:
				case ISLITERAL:
				case REGEX:
				case IF:
				case COALESCE:
				case IRI_REF:
				case PNAME_NS:
				case PNAME_LN:
				case OPEN_BRACE:
					{
					setState(503);
					constraint();
					}
					break;
				case VAR1:
				case VAR2:
					{
					setState(504);
					variable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LimitClauseContext extends ParserRuleContext {
		public TerminalNode LIMIT() { return getToken(SparqlParser.LIMIT, 0); }
		public TerminalNode INT() { return getToken(SparqlParser.INT, 0); }
		public LimitClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterLimitClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitLimitClause(this);
		}
	}

	public final LimitClauseContext limitClause() throws RecognitionException {
		LimitClauseContext _localctx = new LimitClauseContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			match(LIMIT);
			setState(510);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OffsetClauseContext extends ParserRuleContext {
		public TerminalNode OFFSET() { return getToken(SparqlParser.OFFSET, 0); }
		public TerminalNode INT() { return getToken(SparqlParser.INT, 0); }
		public OffsetClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_offsetClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterOffsetClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitOffsetClause(this);
		}
	}

	public final OffsetClauseContext offsetClause() throws RecognitionException {
		OffsetClauseContext _localctx = new OffsetClauseContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_offsetClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512);
			match(OFFSET);
			setState(513);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupGraphPatternContext extends ParserRuleContext {
		public TerminalNode OPEN_CURLY_BRACE() { return getToken(SparqlParser.OPEN_CURLY_BRACE, 0); }
		public TerminalNode CLOSE_CURLY_BRACE() { return getToken(SparqlParser.CLOSE_CURLY_BRACE, 0); }
		public SubSelectContext subSelect() {
			return getRuleContext(SubSelectContext.class,0);
		}
		public GroupGraphPatternSubContext groupGraphPatternSub() {
			return getRuleContext(GroupGraphPatternSubContext.class,0);
		}
		public GroupGraphPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupGraphPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGroupGraphPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGroupGraphPattern(this);
		}
	}

	public final GroupGraphPatternContext groupGraphPattern() throws RecognitionException {
		GroupGraphPatternContext _localctx = new GroupGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_groupGraphPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(515);
			match(OPEN_CURLY_BRACE);
			setState(518);
			switch (_input.LA(1)) {
			case SELECT:
				{
				setState(516);
				subSelect();
				}
				break;
			case INT:
			case EXISTS:
			case UNSAID:
			case NOT:
			case OPTIONAL:
			case GRAPH:
			case FILTER:
			case TRUE:
			case FALSE:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:
			case NIL:
			case ANON:
			case BLANK_NODE_LABEL:
			case OPEN_CURLY_BRACE:
			case CLOSE_CURLY_BRACE:
			case OPEN_BRACE:
			case OPEN_SQUARE_BRACE:
				{
				setState(517);
				groupGraphPatternSub();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(520);
			match(CLOSE_CURLY_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupGraphPatternSubContext extends ParserRuleContext {
		public List<TriplesBlockContext> triplesBlock() {
			return getRuleContexts(TriplesBlockContext.class);
		}
		public TriplesBlockContext triplesBlock(int i) {
			return getRuleContext(TriplesBlockContext.class,i);
		}
		public List<GraphPatternNotTriplesContext> graphPatternNotTriples() {
			return getRuleContexts(GraphPatternNotTriplesContext.class);
		}
		public GraphPatternNotTriplesContext graphPatternNotTriples(int i) {
			return getRuleContext(GraphPatternNotTriplesContext.class,i);
		}
		public List<FilterContext> filter() {
			return getRuleContexts(FilterContext.class);
		}
		public FilterContext filter(int i) {
			return getRuleContext(FilterContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(SparqlParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(SparqlParser.DOT, i);
		}
		public GroupGraphPatternSubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupGraphPatternSub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGroupGraphPatternSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGroupGraphPatternSub(this);
		}
	}

	public final GroupGraphPatternSubContext groupGraphPatternSub() throws RecognitionException {
		GroupGraphPatternSubContext _localctx = new GroupGraphPatternSubContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_groupGraphPatternSub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << TRUE) | (1L << FALSE) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PNAME_LN - 64)) | (1L << (VAR1 - 64)) | (1L << (VAR2 - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (STRING_LITERAL_LONG1 - 64)) | (1L << (STRING_LITERAL_LONG2 - 64)) | (1L << (NIL - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (OPEN_SQUARE_BRACE - 64)))) != 0)) {
				{
				setState(522);
				triplesBlock();
				}
			}

			setState(537);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXISTS) | (1L << UNSAID) | (1L << NOT) | (1L << OPTIONAL) | (1L << GRAPH) | (1L << FILTER))) != 0) || _la==OPEN_CURLY_BRACE) {
				{
				{
				setState(527);
				switch (_input.LA(1)) {
				case EXISTS:
				case UNSAID:
				case NOT:
				case OPTIONAL:
				case GRAPH:
				case OPEN_CURLY_BRACE:
					{
					setState(525);
					graphPatternNotTriples();
					}
					break;
				case FILTER:
					{
					setState(526);
					filter();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(530);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(529);
					match(DOT);
					}
				}

				setState(533);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << TRUE) | (1L << FALSE) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PNAME_LN - 64)) | (1L << (VAR1 - 64)) | (1L << (VAR2 - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (STRING_LITERAL_LONG1 - 64)) | (1L << (STRING_LITERAL_LONG2 - 64)) | (1L << (NIL - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (OPEN_SQUARE_BRACE - 64)))) != 0)) {
					{
					setState(532);
					triplesBlock();
					}
				}

				}
				}
				setState(539);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProjectContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(SparqlParser.SELECT, 0); }
		public TerminalNode ASTERISK() { return getToken(SparqlParser.ASTERISK, 0); }
		public TerminalNode DISTINCT() { return getToken(SparqlParser.DISTINCT, 0); }
		public TerminalNode REDUCED() { return getToken(SparqlParser.REDUCED, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public BuiltInCallContext builtInCall() {
			return getRuleContext(BuiltInCallContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public List<TerminalNode> OPEN_BRACE() { return getTokens(SparqlParser.OPEN_BRACE); }
		public TerminalNode OPEN_BRACE(int i) {
			return getToken(SparqlParser.OPEN_BRACE, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> CLOSE_BRACE() { return getTokens(SparqlParser.CLOSE_BRACE); }
		public TerminalNode CLOSE_BRACE(int i) {
			return getToken(SparqlParser.CLOSE_BRACE, i);
		}
		public List<TerminalNode> AS() { return getTokens(SparqlParser.AS); }
		public TerminalNode AS(int i) {
			return getToken(SparqlParser.AS, i);
		}
		public ProjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_project; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterProject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitProject(this);
		}
	}

	public final ProjectContext project() throws RecognitionException {
		ProjectContext _localctx = new ProjectContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_project);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(540);
			match(SELECT);
			setState(542);
			_la = _input.LA(1);
			if (_la==DISTINCT || _la==REDUCED) {
				{
				setState(541);
				_la = _input.LA(1);
				if ( !(_la==DISTINCT || _la==REDUCED) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(562);
			switch (_input.LA(1)) {
			case ASTERISK:
				{
				setState(544);
				match(ASTERISK);
				}
				break;
			case EXISTS:
			case UNSAID:
			case NOT:
			case STR:
			case LANG:
			case LANGMATCHES:
			case DATATYPE:
			case BOUND:
			case SAMETERM:
			case ISIRI:
			case ISURI:
			case ISBLANK:
			case ISLITERAL:
			case REGEX:
			case IF:
			case COALESCE:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
			case OPEN_BRACE:
				{
				setState(560);
				switch (_input.LA(1)) {
				case VAR1:
				case VAR2:
					{
					setState(545);
					variable();
					}
					break;
				case EXISTS:
				case UNSAID:
				case NOT:
				case STR:
				case LANG:
				case LANGMATCHES:
				case DATATYPE:
				case BOUND:
				case SAMETERM:
				case ISIRI:
				case ISURI:
				case ISBLANK:
				case ISLITERAL:
				case REGEX:
				case IF:
				case COALESCE:
					{
					setState(546);
					builtInCall();
					}
					break;
				case IRI_REF:
				case PNAME_NS:
				case PNAME_LN:
					{
					setState(547);
					functionCall();
					}
					break;
				case OPEN_BRACE:
					{
					setState(556); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(548);
						match(OPEN_BRACE);
						setState(549);
						expression();
						setState(552);
						_la = _input.LA(1);
						if (_la==AS) {
							{
							setState(550);
							match(AS);
							setState(551);
							variable();
							}
						}

						setState(554);
						match(CLOSE_BRACE);
						}
						}
						setState(558); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==OPEN_BRACE );
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriplesBlockContext extends ParserRuleContext {
		public TriplesSameSubjectPathContext triplesSameSubjectPath() {
			return getRuleContext(TriplesSameSubjectPathContext.class,0);
		}
		public TerminalNode DOT() { return getToken(SparqlParser.DOT, 0); }
		public TriplesBlockContext triplesBlock() {
			return getRuleContext(TriplesBlockContext.class,0);
		}
		public TriplesBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triplesBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterTriplesBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitTriplesBlock(this);
		}
	}

	public final TriplesBlockContext triplesBlock() throws RecognitionException {
		TriplesBlockContext _localctx = new TriplesBlockContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_triplesBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(564);
			triplesSameSubjectPath();
			setState(569);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(565);
				match(DOT);
				setState(567);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << TRUE) | (1L << FALSE) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PNAME_LN - 64)) | (1L << (VAR1 - 64)) | (1L << (VAR2 - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (STRING_LITERAL_LONG1 - 64)) | (1L << (STRING_LITERAL_LONG2 - 64)) | (1L << (NIL - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (OPEN_SQUARE_BRACE - 64)))) != 0)) {
					{
					setState(566);
					triplesBlock();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphPatternNotTriplesContext extends ParserRuleContext {
		public OptionalGraphPatternContext optionalGraphPattern() {
			return getRuleContext(OptionalGraphPatternContext.class,0);
		}
		public GroupOrUnionGraphPatternContext groupOrUnionGraphPattern() {
			return getRuleContext(GroupOrUnionGraphPatternContext.class,0);
		}
		public GraphGraphPatternContext graphGraphPattern() {
			return getRuleContext(GraphGraphPatternContext.class,0);
		}
		public ExistsEltContext existsElt() {
			return getRuleContext(ExistsEltContext.class,0);
		}
		public NotExistsEltContext notExistsElt() {
			return getRuleContext(NotExistsEltContext.class,0);
		}
		public GraphPatternNotTriplesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphPatternNotTriples; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGraphPatternNotTriples(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGraphPatternNotTriples(this);
		}
	}

	public final GraphPatternNotTriplesContext graphPatternNotTriples() throws RecognitionException {
		GraphPatternNotTriplesContext _localctx = new GraphPatternNotTriplesContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_graphPatternNotTriples);
		try {
			setState(576);
			switch (_input.LA(1)) {
			case OPTIONAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(571);
				optionalGraphPattern();
				}
				break;
			case OPEN_CURLY_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(572);
				groupOrUnionGraphPattern();
				}
				break;
			case GRAPH:
				enterOuterAlt(_localctx, 3);
				{
				setState(573);
				graphGraphPattern();
				}
				break;
			case EXISTS:
				enterOuterAlt(_localctx, 4);
				{
				setState(574);
				existsElt();
				}
				break;
			case UNSAID:
			case NOT:
				enterOuterAlt(_localctx, 5);
				{
				setState(575);
				notExistsElt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionalGraphPatternContext extends ParserRuleContext {
		public TerminalNode OPTIONAL() { return getToken(SparqlParser.OPTIONAL, 0); }
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public OptionalGraphPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionalGraphPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterOptionalGraphPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitOptionalGraphPattern(this);
		}
	}

	public final OptionalGraphPatternContext optionalGraphPattern() throws RecognitionException {
		OptionalGraphPatternContext _localctx = new OptionalGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_optionalGraphPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(578);
			match(OPTIONAL);
			setState(579);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphGraphPatternContext extends ParserRuleContext {
		public TerminalNode GRAPH() { return getToken(SparqlParser.GRAPH, 0); }
		public VarOrIRIrefContext varOrIRIref() {
			return getRuleContext(VarOrIRIrefContext.class,0);
		}
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public GraphGraphPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphGraphPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGraphGraphPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGraphGraphPattern(this);
		}
	}

	public final GraphGraphPatternContext graphGraphPattern() throws RecognitionException {
		GraphGraphPatternContext _localctx = new GraphGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_graphGraphPattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			match(GRAPH);
			setState(582);
			varOrIRIref();
			setState(583);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExistsEltContext extends ParserRuleContext {
		public TerminalNode EXISTS() { return getToken(SparqlParser.EXISTS, 0); }
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public ExistsEltContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existsElt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterExistsElt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitExistsElt(this);
		}
	}

	public final ExistsEltContext existsElt() throws RecognitionException {
		ExistsEltContext _localctx = new ExistsEltContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_existsElt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(585);
			match(EXISTS);
			setState(586);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotExistsEltContext extends ParserRuleContext {
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public TerminalNode UNSAID() { return getToken(SparqlParser.UNSAID, 0); }
		public TerminalNode NOT() { return getToken(SparqlParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(SparqlParser.EXISTS, 0); }
		public NotExistsEltContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notExistsElt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterNotExistsElt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitNotExistsElt(this);
		}
	}

	public final NotExistsEltContext notExistsElt() throws RecognitionException {
		NotExistsEltContext _localctx = new NotExistsEltContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_notExistsElt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(591);
			switch (_input.LA(1)) {
			case UNSAID:
				{
				setState(588);
				match(UNSAID);
				}
				break;
			case NOT:
				{
				setState(589);
				match(NOT);
				setState(590);
				match(EXISTS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(593);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupOrUnionGraphPatternContext extends ParserRuleContext {
		public List<GroupGraphPatternContext> groupGraphPattern() {
			return getRuleContexts(GroupGraphPatternContext.class);
		}
		public GroupGraphPatternContext groupGraphPattern(int i) {
			return getRuleContext(GroupGraphPatternContext.class,i);
		}
		public List<TerminalNode> UNION() { return getTokens(SparqlParser.UNION); }
		public TerminalNode UNION(int i) {
			return getToken(SparqlParser.UNION, i);
		}
		public GroupOrUnionGraphPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupOrUnionGraphPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGroupOrUnionGraphPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGroupOrUnionGraphPattern(this);
		}
	}

	public final GroupOrUnionGraphPatternContext groupOrUnionGraphPattern() throws RecognitionException {
		GroupOrUnionGraphPatternContext _localctx = new GroupOrUnionGraphPatternContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_groupOrUnionGraphPattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(595);
			groupGraphPattern();
			setState(600);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==UNION) {
				{
				{
				setState(596);
				match(UNION);
				setState(597);
				groupGraphPattern();
				}
				}
				setState(602);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterContext extends ParserRuleContext {
		public TerminalNode FILTER() { return getToken(SparqlParser.FILTER, 0); }
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitFilter(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(603);
			match(FILTER);
			setState(604);
			constraint();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public BrackettedExpressionContext brackettedExpression() {
			return getRuleContext(BrackettedExpressionContext.class,0);
		}
		public BuiltInCallContext builtInCall() {
			return getRuleContext(BuiltInCallContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_constraint);
		try {
			setState(609);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(606);
				brackettedExpression();
				}
				break;
			case EXISTS:
			case UNSAID:
			case NOT:
			case STR:
			case LANG:
			case LANGMATCHES:
			case DATATYPE:
			case BOUND:
			case SAMETERM:
			case ISIRI:
			case ISURI:
			case ISBLANK:
			case ISLITERAL:
			case REGEX:
			case IF:
			case COALESCE:
				enterOuterAlt(_localctx, 2);
				{
				setState(607);
				builtInCall();
				}
				break;
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
				enterOuterAlt(_localctx, 3);
				{
				setState(608);
				functionCall();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitFunctionCall(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_functionCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(611);
			iriRef();
			setState(612);
			argList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public TerminalNode NIL() { return getToken(SparqlParser.NIL, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(SparqlParser.OPEN_BRACE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(SparqlParser.CLOSE_BRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SparqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SparqlParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_argList);
		int _la;
		try {
			setState(626);
			switch (_input.LA(1)) {
			case NIL:
				enterOuterAlt(_localctx, 1);
				{
				setState(614);
				match(NIL);
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(615);
				match(OPEN_BRACE);
				setState(616);
				expression();
				setState(621);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(617);
					match(COMMA);
					setState(618);
					expression();
					}
					}
					setState(623);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(624);
				match(CLOSE_BRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructTemplateContext extends ParserRuleContext {
		public TerminalNode OPEN_CURLY_BRACE() { return getToken(SparqlParser.OPEN_CURLY_BRACE, 0); }
		public TerminalNode CLOSE_CURLY_BRACE() { return getToken(SparqlParser.CLOSE_CURLY_BRACE, 0); }
		public ConstructTriplesContext constructTriples() {
			return getRuleContext(ConstructTriplesContext.class,0);
		}
		public ConstructTemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructTemplate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterConstructTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitConstructTemplate(this);
		}
	}

	public final ConstructTemplateContext constructTemplate() throws RecognitionException {
		ConstructTemplateContext _localctx = new ConstructTemplateContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_constructTemplate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(628);
			match(OPEN_CURLY_BRACE);
			setState(630);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << TRUE) | (1L << FALSE) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PNAME_LN - 64)) | (1L << (VAR1 - 64)) | (1L << (VAR2 - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (STRING_LITERAL_LONG1 - 64)) | (1L << (STRING_LITERAL_LONG2 - 64)) | (1L << (NIL - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (OPEN_SQUARE_BRACE - 64)))) != 0)) {
				{
				setState(629);
				constructTriples();
				}
			}

			setState(632);
			match(CLOSE_CURLY_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructTriplesContext extends ParserRuleContext {
		public TriplesSameSubjectContext triplesSameSubject() {
			return getRuleContext(TriplesSameSubjectContext.class,0);
		}
		public TerminalNode DOT() { return getToken(SparqlParser.DOT, 0); }
		public ConstructTriplesContext constructTriples() {
			return getRuleContext(ConstructTriplesContext.class,0);
		}
		public ConstructTriplesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructTriples; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterConstructTriples(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitConstructTriples(this);
		}
	}

	public final ConstructTriplesContext constructTriples() throws RecognitionException {
		ConstructTriplesContext _localctx = new ConstructTriplesContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_constructTriples);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(634);
			triplesSameSubject();
			setState(639);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(635);
				match(DOT);
				setState(637);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << TRUE) | (1L << FALSE) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PNAME_LN - 64)) | (1L << (VAR1 - 64)) | (1L << (VAR2 - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (STRING_LITERAL_LONG1 - 64)) | (1L << (STRING_LITERAL_LONG2 - 64)) | (1L << (NIL - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (OPEN_SQUARE_BRACE - 64)))) != 0)) {
					{
					setState(636);
					constructTriples();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriplesSameSubjectContext extends ParserRuleContext {
		public VarOrTermContext varOrTerm() {
			return getRuleContext(VarOrTermContext.class,0);
		}
		public PropertyListNotEmptyContext propertyListNotEmpty() {
			return getRuleContext(PropertyListNotEmptyContext.class,0);
		}
		public TriplesNodeContext triplesNode() {
			return getRuleContext(TriplesNodeContext.class,0);
		}
		public PropertyListContext propertyList() {
			return getRuleContext(PropertyListContext.class,0);
		}
		public TriplesSameSubjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triplesSameSubject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterTriplesSameSubject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitTriplesSameSubject(this);
		}
	}

	public final TriplesSameSubjectContext triplesSameSubject() throws RecognitionException {
		TriplesSameSubjectContext _localctx = new TriplesSameSubjectContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_triplesSameSubject);
		try {
			setState(647);
			switch (_input.LA(1)) {
			case INT:
			case TRUE:
			case FALSE:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:
			case NIL:
			case ANON:
			case BLANK_NODE_LABEL:
				enterOuterAlt(_localctx, 1);
				{
				setState(641);
				varOrTerm();
				setState(642);
				propertyListNotEmpty();
				}
				break;
			case OPEN_BRACE:
			case OPEN_SQUARE_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(644);
				triplesNode();
				setState(645);
				propertyList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyListNotEmptyContext extends ParserRuleContext {
		public List<VerbContext> verb() {
			return getRuleContexts(VerbContext.class);
		}
		public VerbContext verb(int i) {
			return getRuleContext(VerbContext.class,i);
		}
		public List<ObjectListContext> objectList() {
			return getRuleContexts(ObjectListContext.class);
		}
		public ObjectListContext objectList(int i) {
			return getRuleContext(ObjectListContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(SparqlParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SparqlParser.SEMICOLON, i);
		}
		public PropertyListNotEmptyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyListNotEmpty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPropertyListNotEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPropertyListNotEmpty(this);
		}
	}

	public final PropertyListNotEmptyContext propertyListNotEmpty() throws RecognitionException {
		PropertyListNotEmptyContext _localctx = new PropertyListNotEmptyContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_propertyListNotEmpty);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(649);
			verb();
			setState(650);
			objectList();
			setState(659);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(651);
				match(SEMICOLON);
				setState(655);
				_la = _input.LA(1);
				if (((((_la - 40)) & ~0x3f) == 0 && ((1L << (_la - 40)) & ((1L << (A - 40)) | (1L << (IRI_REF - 40)) | (1L << (PNAME_NS - 40)) | (1L << (PNAME_LN - 40)) | (1L << (VAR1 - 40)) | (1L << (VAR2 - 40)))) != 0)) {
					{
					setState(652);
					verb();
					setState(653);
					objectList();
					}
				}

				}
				}
				setState(661);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyListContext extends ParserRuleContext {
		public PropertyListNotEmptyContext propertyListNotEmpty() {
			return getRuleContext(PropertyListNotEmptyContext.class,0);
		}
		public PropertyListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPropertyList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPropertyList(this);
		}
	}

	public final PropertyListContext propertyList() throws RecognitionException {
		PropertyListContext _localctx = new PropertyListContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_propertyList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(663);
			_la = _input.LA(1);
			if (((((_la - 40)) & ~0x3f) == 0 && ((1L << (_la - 40)) & ((1L << (A - 40)) | (1L << (IRI_REF - 40)) | (1L << (PNAME_NS - 40)) | (1L << (PNAME_LN - 40)) | (1L << (VAR1 - 40)) | (1L << (VAR2 - 40)))) != 0)) {
				{
				setState(662);
				propertyListNotEmpty();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectListContext extends ParserRuleContext {
		public List<ObjectContext> object() {
			return getRuleContexts(ObjectContext.class);
		}
		public ObjectContext object(int i) {
			return getRuleContext(ObjectContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SparqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SparqlParser.COMMA, i);
		}
		public ObjectListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterObjectList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitObjectList(this);
		}
	}

	public final ObjectListContext objectList() throws RecognitionException {
		ObjectListContext _localctx = new ObjectListContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_objectList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(665);
			object();
			setState(670);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(666);
				match(COMMA);
				setState(667);
				object();
				}
				}
				setState(672);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectContext extends ParserRuleContext {
		public GraphNodeContext graphNode() {
			return getRuleContext(GraphNodeContext.class,0);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitObject(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(673);
			graphNode();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VerbContext extends ParserRuleContext {
		public VarOrIRIrefContext varOrIRIref() {
			return getRuleContext(VarOrIRIrefContext.class,0);
		}
		public TerminalNode A() { return getToken(SparqlParser.A, 0); }
		public VerbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_verb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterVerb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitVerb(this);
		}
	}

	public final VerbContext verb() throws RecognitionException {
		VerbContext _localctx = new VerbContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_verb);
		try {
			setState(677);
			switch (_input.LA(1)) {
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
				enterOuterAlt(_localctx, 1);
				{
				setState(675);
				varOrIRIref();
				}
				break;
			case A:
				enterOuterAlt(_localctx, 2);
				{
				setState(676);
				match(A);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriplesSameSubjectPathContext extends ParserRuleContext {
		public VarOrTermContext varOrTerm() {
			return getRuleContext(VarOrTermContext.class,0);
		}
		public PropertyListNotEmptyPathContext propertyListNotEmptyPath() {
			return getRuleContext(PropertyListNotEmptyPathContext.class,0);
		}
		public TriplesNodeContext triplesNode() {
			return getRuleContext(TriplesNodeContext.class,0);
		}
		public PropertyListPathContext propertyListPath() {
			return getRuleContext(PropertyListPathContext.class,0);
		}
		public TriplesSameSubjectPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triplesSameSubjectPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterTriplesSameSubjectPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitTriplesSameSubjectPath(this);
		}
	}

	public final TriplesSameSubjectPathContext triplesSameSubjectPath() throws RecognitionException {
		TriplesSameSubjectPathContext _localctx = new TriplesSameSubjectPathContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_triplesSameSubjectPath);
		try {
			setState(685);
			switch (_input.LA(1)) {
			case INT:
			case TRUE:
			case FALSE:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:
			case NIL:
			case ANON:
			case BLANK_NODE_LABEL:
				enterOuterAlt(_localctx, 1);
				{
				setState(679);
				varOrTerm();
				setState(680);
				propertyListNotEmptyPath();
				}
				break;
			case OPEN_BRACE:
			case OPEN_SQUARE_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(682);
				triplesNode();
				setState(683);
				propertyListPath();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyListNotEmptyPathContext extends ParserRuleContext {
		public List<ObjectListContext> objectList() {
			return getRuleContexts(ObjectListContext.class);
		}
		public ObjectListContext objectList(int i) {
			return getRuleContext(ObjectListContext.class,i);
		}
		public List<VerbPathContext> verbPath() {
			return getRuleContexts(VerbPathContext.class);
		}
		public VerbPathContext verbPath(int i) {
			return getRuleContext(VerbPathContext.class,i);
		}
		public List<VerbSimpleContext> verbSimple() {
			return getRuleContexts(VerbSimpleContext.class);
		}
		public VerbSimpleContext verbSimple(int i) {
			return getRuleContext(VerbSimpleContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(SparqlParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SparqlParser.SEMICOLON, i);
		}
		public PropertyListNotEmptyPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyListNotEmptyPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPropertyListNotEmptyPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPropertyListNotEmptyPath(this);
		}
	}

	public final PropertyListNotEmptyPathContext propertyListNotEmptyPath() throws RecognitionException {
		PropertyListNotEmptyPathContext _localctx = new PropertyListNotEmptyPathContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_propertyListNotEmptyPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(689);
			switch (_input.LA(1)) {
			case T__0:
			case A:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case OPEN_BRACE:
				{
				setState(687);
				verbPath();
				}
				break;
			case VAR1:
			case VAR2:
				{
				setState(688);
				verbSimple();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(691);
			objectList();
			setState(703);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(692);
				match(SEMICOLON);
				setState(699);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << A) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PNAME_LN - 64)) | (1L << (VAR1 - 64)) | (1L << (VAR2 - 64)) | (1L << (OPEN_BRACE - 64)))) != 0)) {
					{
					setState(695);
					switch (_input.LA(1)) {
					case T__0:
					case A:
					case IRI_REF:
					case PNAME_NS:
					case PNAME_LN:
					case OPEN_BRACE:
						{
						setState(693);
						verbPath();
						}
						break;
					case VAR1:
					case VAR2:
						{
						setState(694);
						verbSimple();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(697);
					objectList();
					}
				}

				}
				}
				setState(705);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyListPathContext extends ParserRuleContext {
		public PropertyListNotEmptyContext propertyListNotEmpty() {
			return getRuleContext(PropertyListNotEmptyContext.class,0);
		}
		public PropertyListPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyListPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPropertyListPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPropertyListPath(this);
		}
	}

	public final PropertyListPathContext propertyListPath() throws RecognitionException {
		PropertyListPathContext _localctx = new PropertyListPathContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_propertyListPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(707);
			_la = _input.LA(1);
			if (((((_la - 40)) & ~0x3f) == 0 && ((1L << (_la - 40)) & ((1L << (A - 40)) | (1L << (IRI_REF - 40)) | (1L << (PNAME_NS - 40)) | (1L << (PNAME_LN - 40)) | (1L << (VAR1 - 40)) | (1L << (VAR2 - 40)))) != 0)) {
				{
				setState(706);
				propertyListNotEmpty();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VerbPathContext extends ParserRuleContext {
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public VerbPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_verbPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterVerbPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitVerbPath(this);
		}
	}

	public final VerbPathContext verbPath() throws RecognitionException {
		VerbPathContext _localctx = new VerbPathContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_verbPath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(709);
			path();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VerbSimpleContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VerbSimpleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_verbSimple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterVerbSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitVerbSimple(this);
		}
	}

	public final VerbSimpleContext verbSimple() throws RecognitionException {
		VerbSimpleContext _localctx = new VerbSimpleContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_verbSimple);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(711);
			variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathUnitContext extends ParserRuleContext {
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public PathUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPathUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPathUnit(this);
		}
	}

	public final PathUnitContext pathUnit() throws RecognitionException {
		PathUnitContext _localctx = new PathUnitContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_pathUnit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(713);
			path();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathContext extends ParserRuleContext {
		public PathAlternativeContext pathAlternative() {
			return getRuleContext(PathAlternativeContext.class,0);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPath(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_path);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(715);
			pathAlternative();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathAlternativeContext extends ParserRuleContext {
		public PathSequenceContext pathSequence() {
			return getRuleContext(PathSequenceContext.class,0);
		}
		public PathAlternativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathAlternative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPathAlternative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPathAlternative(this);
		}
	}

	public final PathAlternativeContext pathAlternative() throws RecognitionException {
		PathAlternativeContext _localctx = new PathAlternativeContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_pathAlternative);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(717);
			pathSequence();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathSequenceContext extends ParserRuleContext {
		public List<PathEltOrReverseContext> pathEltOrReverse() {
			return getRuleContexts(PathEltOrReverseContext.class);
		}
		public PathEltOrReverseContext pathEltOrReverse(int i) {
			return getRuleContext(PathEltOrReverseContext.class,i);
		}
		public List<TerminalNode> DIVIDE() { return getTokens(SparqlParser.DIVIDE); }
		public TerminalNode DIVIDE(int i) {
			return getToken(SparqlParser.DIVIDE, i);
		}
		public List<PathEltContext> pathElt() {
			return getRuleContexts(PathEltContext.class);
		}
		public PathEltContext pathElt(int i) {
			return getRuleContext(PathEltContext.class,i);
		}
		public PathSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathSequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPathSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPathSequence(this);
		}
	}

	public final PathSequenceContext pathSequence() throws RecognitionException {
		PathSequenceContext _localctx = new PathSequenceContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_pathSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(719);
			pathEltOrReverse();
			setState(726);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==DIVIDE) {
				{
				setState(724);
				switch (_input.LA(1)) {
				case DIVIDE:
					{
					setState(720);
					match(DIVIDE);
					setState(721);
					pathEltOrReverse();
					}
					break;
				case T__0:
					{
					setState(722);
					match(T__0);
					setState(723);
					pathElt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(728);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathEltContext extends ParserRuleContext {
		public PathPrimaryContext pathPrimary() {
			return getRuleContext(PathPrimaryContext.class,0);
		}
		public PathModContext pathMod() {
			return getRuleContext(PathModContext.class,0);
		}
		public PathEltContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathElt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPathElt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPathElt(this);
		}
	}

	public final PathEltContext pathElt() throws RecognitionException {
		PathEltContext _localctx = new PathEltContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_pathElt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(729);
			pathPrimary();
			setState(731);
			_la = _input.LA(1);
			if (_la==T__1 || ((((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & ((1L << (OPEN_CURLY_BRACE - 88)) | (1L << (PLUS - 88)) | (1L << (ASTERISK - 88)))) != 0)) {
				{
				setState(730);
				pathMod();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathEltOrReverseContext extends ParserRuleContext {
		public PathEltContext pathElt() {
			return getRuleContext(PathEltContext.class,0);
		}
		public PathEltOrReverseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathEltOrReverse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPathEltOrReverse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPathEltOrReverse(this);
		}
	}

	public final PathEltOrReverseContext pathEltOrReverse() throws RecognitionException {
		PathEltOrReverseContext _localctx = new PathEltOrReverseContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_pathEltOrReverse);
		try {
			setState(736);
			switch (_input.LA(1)) {
			case A:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(733);
				pathElt();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(734);
				match(T__0);
				setState(735);
				pathElt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathModContext extends ParserRuleContext {
		public TerminalNode ASTERISK() { return getToken(SparqlParser.ASTERISK, 0); }
		public TerminalNode PLUS() { return getToken(SparqlParser.PLUS, 0); }
		public TerminalNode OPEN_CURLY_BRACE() { return getToken(SparqlParser.OPEN_CURLY_BRACE, 0); }
		public List<TerminalNode> INTEGER() { return getTokens(SparqlParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(SparqlParser.INTEGER, i);
		}
		public TerminalNode COMMA() { return getToken(SparqlParser.COMMA, 0); }
		public TerminalNode CLOSE_CURLY_BRACE() { return getToken(SparqlParser.CLOSE_CURLY_BRACE, 0); }
		public PathModContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathMod; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPathMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPathMod(this);
		}
	}

	public final PathModContext pathMod() throws RecognitionException {
		PathModContext _localctx = new PathModContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_pathMod);
		try {
			setState(752);
			switch (_input.LA(1)) {
			case ASTERISK:
				enterOuterAlt(_localctx, 1);
				{
				setState(738);
				match(ASTERISK);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(739);
				match(T__1);
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(740);
				match(PLUS);
				}
				break;
			case OPEN_CURLY_BRACE:
				enterOuterAlt(_localctx, 4);
				{
				setState(741);
				match(OPEN_CURLY_BRACE);
				{
				setState(742);
				match(INTEGER);
				setState(750);
				switch (_input.LA(1)) {
				case COMMA:
					{
					setState(743);
					match(COMMA);
					setState(747);
					switch (_input.LA(1)) {
					case CLOSE_CURLY_BRACE:
						{
						setState(744);
						match(CLOSE_CURLY_BRACE);
						}
						break;
					case INTEGER:
						{
						setState(745);
						match(INTEGER);
						setState(746);
						match(CLOSE_CURLY_BRACE);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				case CLOSE_CURLY_BRACE:
					{
					setState(749);
					match(CLOSE_CURLY_BRACE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathPrimaryContext extends ParserRuleContext {
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public TerminalNode A() { return getToken(SparqlParser.A, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(SparqlParser.OPEN_BRACE, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(SparqlParser.CLOSE_BRACE, 0); }
		public PathPrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathPrimary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPathPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPathPrimary(this);
		}
	}

	public final PathPrimaryContext pathPrimary() throws RecognitionException {
		PathPrimaryContext _localctx = new PathPrimaryContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_pathPrimary);
		try {
			setState(760);
			switch (_input.LA(1)) {
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
				enterOuterAlt(_localctx, 1);
				{
				setState(754);
				iriRef();
				}
				break;
			case A:
				enterOuterAlt(_localctx, 2);
				{
				setState(755);
				match(A);
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(756);
				match(OPEN_BRACE);
				setState(757);
				path();
				setState(758);
				match(CLOSE_BRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriplesNodeContext extends ParserRuleContext {
		public CollectionContext collection() {
			return getRuleContext(CollectionContext.class,0);
		}
		public BlankNodePropertyListContext blankNodePropertyList() {
			return getRuleContext(BlankNodePropertyListContext.class,0);
		}
		public TriplesNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triplesNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterTriplesNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitTriplesNode(this);
		}
	}

	public final TriplesNodeContext triplesNode() throws RecognitionException {
		TriplesNodeContext _localctx = new TriplesNodeContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_triplesNode);
		try {
			setState(764);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(762);
				collection();
				}
				break;
			case OPEN_SQUARE_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(763);
				blankNodePropertyList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlankNodePropertyListContext extends ParserRuleContext {
		public TerminalNode OPEN_SQUARE_BRACE() { return getToken(SparqlParser.OPEN_SQUARE_BRACE, 0); }
		public PropertyListNotEmptyContext propertyListNotEmpty() {
			return getRuleContext(PropertyListNotEmptyContext.class,0);
		}
		public TerminalNode CLOSE_SQUARE_BRACE() { return getToken(SparqlParser.CLOSE_SQUARE_BRACE, 0); }
		public BlankNodePropertyListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blankNodePropertyList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterBlankNodePropertyList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitBlankNodePropertyList(this);
		}
	}

	public final BlankNodePropertyListContext blankNodePropertyList() throws RecognitionException {
		BlankNodePropertyListContext _localctx = new BlankNodePropertyListContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_blankNodePropertyList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(766);
			match(OPEN_SQUARE_BRACE);
			setState(767);
			propertyListNotEmpty();
			setState(768);
			match(CLOSE_SQUARE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CollectionContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(SparqlParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(SparqlParser.CLOSE_BRACE, 0); }
		public List<GraphNodeContext> graphNode() {
			return getRuleContexts(GraphNodeContext.class);
		}
		public GraphNodeContext graphNode(int i) {
			return getRuleContext(GraphNodeContext.class,i);
		}
		public CollectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterCollection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitCollection(this);
		}
	}

	public final CollectionContext collection() throws RecognitionException {
		CollectionContext _localctx = new CollectionContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_collection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(770);
			match(OPEN_BRACE);
			setState(772); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(771);
				graphNode();
				}
				}
				setState(774); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << TRUE) | (1L << FALSE) | (1L << IRI_REF) | (1L << PNAME_NS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (PNAME_LN - 64)) | (1L << (VAR1 - 64)) | (1L << (VAR2 - 64)) | (1L << (DECIMAL - 64)) | (1L << (DOUBLE - 64)) | (1L << (INTEGER_POSITIVE - 64)) | (1L << (DECIMAL_POSITIVE - 64)) | (1L << (DOUBLE_POSITIVE - 64)) | (1L << (INTEGER_NEGATIVE - 64)) | (1L << (DECIMAL_NEGATIVE - 64)) | (1L << (DOUBLE_NEGATIVE - 64)) | (1L << (STRING_LITERAL1 - 64)) | (1L << (STRING_LITERAL2 - 64)) | (1L << (STRING_LITERAL_LONG1 - 64)) | (1L << (STRING_LITERAL_LONG2 - 64)) | (1L << (NIL - 64)) | (1L << (ANON - 64)) | (1L << (BLANK_NODE_LABEL - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (OPEN_SQUARE_BRACE - 64)))) != 0) );
			setState(776);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphNodeContext extends ParserRuleContext {
		public VarOrTermContext varOrTerm() {
			return getRuleContext(VarOrTermContext.class,0);
		}
		public TriplesNodeContext triplesNode() {
			return getRuleContext(TriplesNodeContext.class,0);
		}
		public GraphNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGraphNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGraphNode(this);
		}
	}

	public final GraphNodeContext graphNode() throws RecognitionException {
		GraphNodeContext _localctx = new GraphNodeContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_graphNode);
		try {
			setState(780);
			switch (_input.LA(1)) {
			case INT:
			case TRUE:
			case FALSE:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:
			case NIL:
			case ANON:
			case BLANK_NODE_LABEL:
				enterOuterAlt(_localctx, 1);
				{
				setState(778);
				varOrTerm();
				}
				break;
			case OPEN_BRACE:
			case OPEN_SQUARE_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(779);
				triplesNode();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarOrTermContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public GraphTermContext graphTerm() {
			return getRuleContext(GraphTermContext.class,0);
		}
		public VarOrTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varOrTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterVarOrTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitVarOrTerm(this);
		}
	}

	public final VarOrTermContext varOrTerm() throws RecognitionException {
		VarOrTermContext _localctx = new VarOrTermContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_varOrTerm);
		try {
			setState(784);
			switch (_input.LA(1)) {
			case VAR1:
			case VAR2:
				enterOuterAlt(_localctx, 1);
				{
				setState(782);
				variable();
				}
				break;
			case INT:
			case TRUE:
			case FALSE:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:
			case NIL:
			case ANON:
			case BLANK_NODE_LABEL:
				enterOuterAlt(_localctx, 2);
				{
				setState(783);
				graphTerm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarOrIRIrefContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public VarOrIRIrefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varOrIRIref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterVarOrIRIref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitVarOrIRIref(this);
		}
	}

	public final VarOrIRIrefContext varOrIRIref() throws RecognitionException {
		VarOrIRIrefContext _localctx = new VarOrIRIrefContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_varOrIRIref);
		try {
			setState(788);
			switch (_input.LA(1)) {
			case VAR1:
			case VAR2:
				enterOuterAlt(_localctx, 1);
				{
				setState(786);
				variable();
				}
				break;
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
				enterOuterAlt(_localctx, 2);
				{
				setState(787);
				iriRef();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VAR1() { return getToken(SparqlParser.VAR1, 0); }
		public TerminalNode VAR2() { return getToken(SparqlParser.VAR2, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(790);
			_la = _input.LA(1);
			if ( !(_la==VAR1 || _la==VAR2) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GraphTermContext extends ParserRuleContext {
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public RdfLiteralContext rdfLiteral() {
			return getRuleContext(RdfLiteralContext.class,0);
		}
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public BlankNodeContext blankNode() {
			return getRuleContext(BlankNodeContext.class,0);
		}
		public TerminalNode NIL() { return getToken(SparqlParser.NIL, 0); }
		public GraphTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterGraphTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitGraphTerm(this);
		}
	}

	public final GraphTermContext graphTerm() throws RecognitionException {
		GraphTermContext _localctx = new GraphTermContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_graphTerm);
		try {
			setState(798);
			switch (_input.LA(1)) {
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
				enterOuterAlt(_localctx, 1);
				{
				setState(792);
				iriRef();
				}
				break;
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:
				enterOuterAlt(_localctx, 2);
				{
				setState(793);
				rdfLiteral();
				}
				break;
			case INT:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(794);
				numericLiteral();
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(795);
				booleanLiteral();
				}
				break;
			case ANON:
			case BLANK_NODE_LABEL:
				enterOuterAlt(_localctx, 5);
				{
				setState(796);
				blankNode();
				}
				break;
			case NIL:
				enterOuterAlt(_localctx, 6);
				{
				setState(797);
				match(NIL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ConditionalOrExpressionContext conditionalOrExpression() {
			return getRuleContext(ConditionalOrExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(800);
			conditionalOrExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalOrExpressionContext extends ParserRuleContext {
		public List<ConditionalAndExpressionContext> conditionalAndExpression() {
			return getRuleContexts(ConditionalAndExpressionContext.class);
		}
		public ConditionalAndExpressionContext conditionalAndExpression(int i) {
			return getRuleContext(ConditionalAndExpressionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(SparqlParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(SparqlParser.OR, i);
		}
		public ConditionalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalOrExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterConditionalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitConditionalOrExpression(this);
		}
	}

	public final ConditionalOrExpressionContext conditionalOrExpression() throws RecognitionException {
		ConditionalOrExpressionContext _localctx = new ConditionalOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_conditionalOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(802);
			conditionalAndExpression();
			setState(807);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(803);
				match(OR);
				setState(804);
				conditionalAndExpression();
				}
				}
				setState(809);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalAndExpressionContext extends ParserRuleContext {
		public List<ValueLogicalContext> valueLogical() {
			return getRuleContexts(ValueLogicalContext.class);
		}
		public ValueLogicalContext valueLogical(int i) {
			return getRuleContext(ValueLogicalContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(SparqlParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(SparqlParser.AND, i);
		}
		public ConditionalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterConditionalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitConditionalAndExpression(this);
		}
	}

	public final ConditionalAndExpressionContext conditionalAndExpression() throws RecognitionException {
		ConditionalAndExpressionContext _localctx = new ConditionalAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_conditionalAndExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(810);
			valueLogical();
			setState(815);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(811);
				match(AND);
				setState(812);
				valueLogical();
				}
				}
				setState(817);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueLogicalContext extends ParserRuleContext {
		public RelationalExpressionContext relationalExpression() {
			return getRuleContext(RelationalExpressionContext.class,0);
		}
		public ValueLogicalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueLogical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterValueLogical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitValueLogical(this);
		}
	}

	public final ValueLogicalContext valueLogical() throws RecognitionException {
		ValueLogicalContext _localctx = new ValueLogicalContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_valueLogical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(818);
			relationalExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalExpressionContext extends ParserRuleContext {
		public List<NumericExpressionContext> numericExpression() {
			return getRuleContexts(NumericExpressionContext.class);
		}
		public NumericExpressionContext numericExpression(int i) {
			return getRuleContext(NumericExpressionContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(SparqlParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(SparqlParser.NOT_EQUAL, 0); }
		public TerminalNode LESS() { return getToken(SparqlParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(SparqlParser.GREATER, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(SparqlParser.LESS_EQUAL, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(SparqlParser.GREATER_EQUAL, 0); }
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitRelationalExpression(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_relationalExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(820);
			numericExpression();
			setState(833);
			switch (_input.LA(1)) {
			case EQUAL:
				{
				setState(821);
				match(EQUAL);
				setState(822);
				numericExpression();
				}
				break;
			case NOT_EQUAL:
				{
				setState(823);
				match(NOT_EQUAL);
				setState(824);
				numericExpression();
				}
				break;
			case LESS:
				{
				setState(825);
				match(LESS);
				setState(826);
				numericExpression();
				}
				break;
			case GREATER:
				{
				setState(827);
				match(GREATER);
				setState(828);
				numericExpression();
				}
				break;
			case LESS_EQUAL:
				{
				setState(829);
				match(LESS_EQUAL);
				setState(830);
				numericExpression();
				}
				break;
			case GREATER_EQUAL:
				{
				setState(831);
				match(GREATER_EQUAL);
				setState(832);
				numericExpression();
				}
				break;
			case AS:
			case AND:
			case OR:
			case COMMA:
			case CLOSE_BRACE:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericExpressionContext extends ParserRuleContext {
		public AdditiveExpressionContext additiveExpression() {
			return getRuleContext(AdditiveExpressionContext.class,0);
		}
		public NumericExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterNumericExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitNumericExpression(this);
		}
	}

	public final NumericExpressionContext numericExpression() throws RecognitionException {
		NumericExpressionContext _localctx = new NumericExpressionContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_numericExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(835);
			additiveExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(SparqlParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(SparqlParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(SparqlParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(SparqlParser.MINUS, i);
		}
		public List<NumericLiteralPositiveContext> numericLiteralPositive() {
			return getRuleContexts(NumericLiteralPositiveContext.class);
		}
		public NumericLiteralPositiveContext numericLiteralPositive(int i) {
			return getRuleContext(NumericLiteralPositiveContext.class,i);
		}
		public List<NumericLiteralNegativeContext> numericLiteralNegative() {
			return getRuleContexts(NumericLiteralNegativeContext.class);
		}
		public NumericLiteralNegativeContext numericLiteralNegative(int i) {
			return getRuleContext(NumericLiteralNegativeContext.class,i);
		}
		public List<TerminalNode> ASTERISK() { return getTokens(SparqlParser.ASTERISK); }
		public TerminalNode ASTERISK(int i) {
			return getToken(SparqlParser.ASTERISK, i);
		}
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public List<TerminalNode> DIVIDE() { return getTokens(SparqlParser.DIVIDE); }
		public TerminalNode DIVIDE(int i) {
			return getToken(SparqlParser.DIVIDE, i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitAdditiveExpression(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(837);
			multiplicativeExpression();
			setState(854);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (INTEGER_POSITIVE - 71)) | (1L << (DECIMAL_POSITIVE - 71)) | (1L << (DOUBLE_POSITIVE - 71)) | (1L << (INTEGER_NEGATIVE - 71)) | (1L << (DECIMAL_NEGATIVE - 71)) | (1L << (DOUBLE_NEGATIVE - 71)) | (1L << (PLUS - 71)) | (1L << (MINUS - 71)))) != 0)) {
				{
				setState(852);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(838);
					match(PLUS);
					setState(839);
					multiplicativeExpression();
					}
					break;
				case MINUS:
					{
					setState(840);
					match(MINUS);
					setState(841);
					multiplicativeExpression();
					}
					break;
				case INTEGER_POSITIVE:
				case DECIMAL_POSITIVE:
				case DOUBLE_POSITIVE:
				case INTEGER_NEGATIVE:
				case DECIMAL_NEGATIVE:
				case DOUBLE_NEGATIVE:
					{
					setState(844);
					switch (_input.LA(1)) {
					case INTEGER_POSITIVE:
					case DECIMAL_POSITIVE:
					case DOUBLE_POSITIVE:
						{
						setState(842);
						numericLiteralPositive();
						}
						break;
					case INTEGER_NEGATIVE:
					case DECIMAL_NEGATIVE:
					case DOUBLE_NEGATIVE:
						{
						setState(843);
						numericLiteralNegative();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(850);
					switch (_input.LA(1)) {
					case ASTERISK:
						{
						setState(846);
						match(ASTERISK);
						setState(847);
						unaryExpression();
						}
						break;
					case DIVIDE:
						{
						setState(848);
						match(DIVIDE);
						setState(849);
						unaryExpression();
						}
						break;
					case AS:
					case INTEGER_POSITIVE:
					case DECIMAL_POSITIVE:
					case DOUBLE_POSITIVE:
					case INTEGER_NEGATIVE:
					case DECIMAL_NEGATIVE:
					case DOUBLE_NEGATIVE:
					case AND:
					case OR:
					case PLUS:
					case MINUS:
					case COMMA:
					case EQUAL:
					case LESS:
					case GREATER:
					case CLOSE_BRACE:
					case LESS_EQUAL:
					case GREATER_EQUAL:
					case NOT_EQUAL:
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(856);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public List<UnaryExpressionContext> unaryExpression() {
			return getRuleContexts(UnaryExpressionContext.class);
		}
		public UnaryExpressionContext unaryExpression(int i) {
			return getRuleContext(UnaryExpressionContext.class,i);
		}
		public List<TerminalNode> ASTERISK() { return getTokens(SparqlParser.ASTERISK); }
		public TerminalNode ASTERISK(int i) {
			return getToken(SparqlParser.ASTERISK, i);
		}
		public List<TerminalNode> DIVIDE() { return getTokens(SparqlParser.DIVIDE); }
		public TerminalNode DIVIDE(int i) {
			return getToken(SparqlParser.DIVIDE, i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitMultiplicativeExpression(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(857);
			unaryExpression();
			setState(864);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ASTERISK || _la==DIVIDE) {
				{
				setState(862);
				switch (_input.LA(1)) {
				case ASTERISK:
					{
					setState(858);
					match(ASTERISK);
					setState(859);
					unaryExpression();
					}
					break;
				case DIVIDE:
					{
					setState(860);
					match(DIVIDE);
					setState(861);
					unaryExpression();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(866);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public TerminalNode NOT_SIGN() { return getToken(SparqlParser.NOT_SIGN, 0); }
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(SparqlParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(SparqlParser.MINUS, 0); }
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitUnaryExpression(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_unaryExpression);
		try {
			setState(874);
			switch (_input.LA(1)) {
			case NOT_SIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(867);
				match(NOT_SIGN);
				setState(868);
				primaryExpression();
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(869);
				match(PLUS);
				setState(870);
				primaryExpression();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(871);
				match(MINUS);
				setState(872);
				primaryExpression();
				}
				break;
			case INT:
			case EXISTS:
			case UNSAID:
			case NOT:
			case STR:
			case LANG:
			case LANGMATCHES:
			case DATATYPE:
			case BOUND:
			case SAMETERM:
			case ISIRI:
			case ISURI:
			case ISBLANK:
			case ISLITERAL:
			case REGEX:
			case COUNT:
			case SUM:
			case MIN:
			case MAX:
			case AVG:
			case TRUE:
			case FALSE:
			case IF:
			case COALESCE:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 4);
				{
				setState(873);
				primaryExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public BrackettedExpressionContext brackettedExpression() {
			return getRuleContext(BrackettedExpressionContext.class,0);
		}
		public BuiltInCallContext builtInCall() {
			return getRuleContext(BuiltInCallContext.class,0);
		}
		public IriRefOrFunctionContext iriRefOrFunction() {
			return getRuleContext(IriRefOrFunctionContext.class,0);
		}
		public RdfLiteralContext rdfLiteral() {
			return getRuleContext(RdfLiteralContext.class,0);
		}
		public NumericLiteralContext numericLiteral() {
			return getRuleContext(NumericLiteralContext.class,0);
		}
		public BooleanLiteralContext booleanLiteral() {
			return getRuleContext(BooleanLiteralContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public AggregateContext aggregate() {
			return getRuleContext(AggregateContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPrimaryExpression(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_primaryExpression);
		try {
			setState(884);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(876);
				brackettedExpression();
				}
				break;
			case EXISTS:
			case UNSAID:
			case NOT:
			case STR:
			case LANG:
			case LANGMATCHES:
			case DATATYPE:
			case BOUND:
			case SAMETERM:
			case ISIRI:
			case ISURI:
			case ISBLANK:
			case ISLITERAL:
			case REGEX:
			case IF:
			case COALESCE:
				enterOuterAlt(_localctx, 2);
				{
				setState(877);
				builtInCall();
				}
				break;
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
				enterOuterAlt(_localctx, 3);
				{
				setState(878);
				iriRefOrFunction();
				}
				break;
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:
				enterOuterAlt(_localctx, 4);
				{
				setState(879);
				rdfLiteral();
				}
				break;
			case INT:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
				enterOuterAlt(_localctx, 5);
				{
				setState(880);
				numericLiteral();
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 6);
				{
				setState(881);
				booleanLiteral();
				}
				break;
			case VAR1:
			case VAR2:
				enterOuterAlt(_localctx, 7);
				{
				setState(882);
				variable();
				}
				break;
			case COUNT:
			case SUM:
			case MIN:
			case MAX:
			case AVG:
				enterOuterAlt(_localctx, 8);
				{
				setState(883);
				aggregate();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BrackettedExpressionContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(SparqlParser.OPEN_BRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(SparqlParser.CLOSE_BRACE, 0); }
		public BrackettedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_brackettedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterBrackettedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitBrackettedExpression(this);
		}
	}

	public final BrackettedExpressionContext brackettedExpression() throws RecognitionException {
		BrackettedExpressionContext _localctx = new BrackettedExpressionContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_brackettedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(886);
			match(OPEN_BRACE);
			setState(887);
			expression();
			setState(888);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BuiltInCallContext extends ParserRuleContext {
		public TerminalNode STR() { return getToken(SparqlParser.STR, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(SparqlParser.OPEN_BRACE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(SparqlParser.CLOSE_BRACE, 0); }
		public TerminalNode LANG() { return getToken(SparqlParser.LANG, 0); }
		public TerminalNode LANGMATCHES() { return getToken(SparqlParser.LANGMATCHES, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SparqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SparqlParser.COMMA, i);
		}
		public TerminalNode DATATYPE() { return getToken(SparqlParser.DATATYPE, 0); }
		public TerminalNode BOUND() { return getToken(SparqlParser.BOUND, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode COALESCE() { return getToken(SparqlParser.COALESCE, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode IF() { return getToken(SparqlParser.IF, 0); }
		public TerminalNode SAMETERM() { return getToken(SparqlParser.SAMETERM, 0); }
		public TerminalNode ISIRI() { return getToken(SparqlParser.ISIRI, 0); }
		public TerminalNode ISURI() { return getToken(SparqlParser.ISURI, 0); }
		public TerminalNode ISBLANK() { return getToken(SparqlParser.ISBLANK, 0); }
		public TerminalNode ISLITERAL() { return getToken(SparqlParser.ISLITERAL, 0); }
		public RegexExpressionContext regexExpression() {
			return getRuleContext(RegexExpressionContext.class,0);
		}
		public ExistsFuncContext existsFunc() {
			return getRuleContext(ExistsFuncContext.class,0);
		}
		public NotExistsFuncContext notExistsFunc() {
			return getRuleContext(NotExistsFuncContext.class,0);
		}
		public BuiltInCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtInCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterBuiltInCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitBuiltInCall(this);
		}
	}

	public final BuiltInCallContext builtInCall() throws RecognitionException {
		BuiltInCallContext _localctx = new BuiltInCallContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_builtInCall);
		try {
			setState(958);
			switch (_input.LA(1)) {
			case STR:
				enterOuterAlt(_localctx, 1);
				{
				setState(890);
				match(STR);
				setState(891);
				match(OPEN_BRACE);
				setState(892);
				expression();
				setState(893);
				match(CLOSE_BRACE);
				}
				break;
			case LANG:
				enterOuterAlt(_localctx, 2);
				{
				setState(895);
				match(LANG);
				setState(896);
				match(OPEN_BRACE);
				setState(897);
				expression();
				setState(898);
				match(CLOSE_BRACE);
				}
				break;
			case LANGMATCHES:
				enterOuterAlt(_localctx, 3);
				{
				setState(900);
				match(LANGMATCHES);
				setState(901);
				match(OPEN_BRACE);
				setState(902);
				expression();
				setState(903);
				match(COMMA);
				setState(904);
				expression();
				setState(905);
				match(CLOSE_BRACE);
				}
				break;
			case DATATYPE:
				enterOuterAlt(_localctx, 4);
				{
				setState(907);
				match(DATATYPE);
				setState(908);
				match(OPEN_BRACE);
				setState(909);
				expression();
				setState(910);
				match(CLOSE_BRACE);
				}
				break;
			case BOUND:
				enterOuterAlt(_localctx, 5);
				{
				setState(912);
				match(BOUND);
				setState(913);
				match(OPEN_BRACE);
				setState(914);
				variable();
				setState(915);
				match(CLOSE_BRACE);
				}
				break;
			case COALESCE:
				enterOuterAlt(_localctx, 6);
				{
				setState(917);
				match(COALESCE);
				setState(918);
				argList();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 7);
				{
				setState(919);
				match(IF);
				setState(920);
				match(OPEN_BRACE);
				setState(921);
				expression();
				setState(922);
				match(COMMA);
				setState(923);
				expression();
				setState(924);
				match(COMMA);
				setState(925);
				expression();
				setState(926);
				match(CLOSE_BRACE);
				}
				break;
			case SAMETERM:
				enterOuterAlt(_localctx, 8);
				{
				setState(928);
				match(SAMETERM);
				setState(929);
				match(OPEN_BRACE);
				setState(930);
				expression();
				setState(931);
				match(COMMA);
				setState(932);
				expression();
				setState(933);
				match(CLOSE_BRACE);
				}
				break;
			case ISIRI:
				enterOuterAlt(_localctx, 9);
				{
				setState(935);
				match(ISIRI);
				setState(936);
				match(OPEN_BRACE);
				setState(937);
				expression();
				setState(938);
				match(CLOSE_BRACE);
				}
				break;
			case ISURI:
				enterOuterAlt(_localctx, 10);
				{
				setState(940);
				match(ISURI);
				setState(941);
				match(OPEN_BRACE);
				setState(942);
				expression();
				setState(943);
				match(CLOSE_BRACE);
				}
				break;
			case ISBLANK:
				enterOuterAlt(_localctx, 11);
				{
				setState(945);
				match(ISBLANK);
				setState(946);
				match(OPEN_BRACE);
				setState(947);
				expression();
				setState(948);
				match(CLOSE_BRACE);
				}
				break;
			case ISLITERAL:
				enterOuterAlt(_localctx, 12);
				{
				setState(950);
				match(ISLITERAL);
				setState(951);
				match(OPEN_BRACE);
				setState(952);
				expression();
				setState(953);
				match(CLOSE_BRACE);
				}
				break;
			case REGEX:
				enterOuterAlt(_localctx, 13);
				{
				setState(955);
				regexExpression();
				}
				break;
			case EXISTS:
				enterOuterAlt(_localctx, 14);
				{
				setState(956);
				existsFunc();
				}
				break;
			case UNSAID:
			case NOT:
				enterOuterAlt(_localctx, 15);
				{
				setState(957);
				notExistsFunc();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubSelectContext extends ParserRuleContext {
		public ProjectContext project() {
			return getRuleContext(ProjectContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public SolutionModifierContext solutionModifier() {
			return getRuleContext(SolutionModifierContext.class,0);
		}
		public SubSelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subSelect; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterSubSelect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitSubSelect(this);
		}
	}

	public final SubSelectContext subSelect() throws RecognitionException {
		SubSelectContext _localctx = new SubSelectContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_subSelect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(960);
			project();
			setState(961);
			whereClause();
			setState(962);
			solutionModifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegexExpressionContext extends ParserRuleContext {
		public TerminalNode REGEX() { return getToken(SparqlParser.REGEX, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(SparqlParser.OPEN_BRACE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SparqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SparqlParser.COMMA, i);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(SparqlParser.CLOSE_BRACE, 0); }
		public RegexExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regexExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterRegexExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitRegexExpression(this);
		}
	}

	public final RegexExpressionContext regexExpression() throws RecognitionException {
		RegexExpressionContext _localctx = new RegexExpressionContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_regexExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(964);
			match(REGEX);
			setState(965);
			match(OPEN_BRACE);
			setState(966);
			expression();
			setState(967);
			match(COMMA);
			setState(968);
			expression();
			setState(971);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(969);
				match(COMMA);
				setState(970);
				expression();
				}
			}

			setState(973);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExistsFuncContext extends ParserRuleContext {
		public TerminalNode EXISTS() { return getToken(SparqlParser.EXISTS, 0); }
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public ExistsFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_existsFunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterExistsFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitExistsFunc(this);
		}
	}

	public final ExistsFuncContext existsFunc() throws RecognitionException {
		ExistsFuncContext _localctx = new ExistsFuncContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_existsFunc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(975);
			match(EXISTS);
			setState(976);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotExistsFuncContext extends ParserRuleContext {
		public GroupGraphPatternContext groupGraphPattern() {
			return getRuleContext(GroupGraphPatternContext.class,0);
		}
		public TerminalNode UNSAID() { return getToken(SparqlParser.UNSAID, 0); }
		public TerminalNode NOT() { return getToken(SparqlParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(SparqlParser.EXISTS, 0); }
		public NotExistsFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notExistsFunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterNotExistsFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitNotExistsFunc(this);
		}
	}

	public final NotExistsFuncContext notExistsFunc() throws RecognitionException {
		NotExistsFuncContext _localctx = new NotExistsFuncContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_notExistsFunc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(981);
			switch (_input.LA(1)) {
			case UNSAID:
				{
				setState(978);
				match(UNSAID);
				}
				break;
			case NOT:
				{
				setState(979);
				match(NOT);
				setState(980);
				match(EXISTS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(983);
			groupGraphPattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggregateContext extends ParserRuleContext {
		public TerminalNode COUNT() { return getToken(SparqlParser.COUNT, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(SparqlParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(SparqlParser.CLOSE_BRACE, 0); }
		public TerminalNode ASTERISK() { return getToken(SparqlParser.ASTERISK, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode DISTINCT() { return getToken(SparqlParser.DISTINCT, 0); }
		public TerminalNode SUM() { return getToken(SparqlParser.SUM, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode MIN() { return getToken(SparqlParser.MIN, 0); }
		public TerminalNode MAX() { return getToken(SparqlParser.MAX, 0); }
		public TerminalNode AVG() { return getToken(SparqlParser.AVG, 0); }
		public AggregateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterAggregate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitAggregate(this);
		}
	}

	public final AggregateContext aggregate() throws RecognitionException {
		AggregateContext _localctx = new AggregateContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_aggregate);
		try {
			setState(1017);
			switch (_input.LA(1)) {
			case COUNT:
				enterOuterAlt(_localctx, 1);
				{
				setState(985);
				match(COUNT);
				setState(986);
				match(OPEN_BRACE);
				setState(994);
				switch (_input.LA(1)) {
				case ASTERISK:
					{
					setState(987);
					match(ASTERISK);
					}
					break;
				case VAR1:
				case VAR2:
					{
					setState(988);
					variable();
					}
					break;
				case DISTINCT:
					{
					setState(989);
					match(DISTINCT);
					setState(992);
					switch (_input.LA(1)) {
					case ASTERISK:
						{
						setState(990);
						match(ASTERISK);
						}
						break;
					case VAR1:
					case VAR2:
						{
						setState(991);
						variable();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(996);
				match(CLOSE_BRACE);
				}
				break;
			case SUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(997);
				match(SUM);
				setState(998);
				match(OPEN_BRACE);
				setState(999);
				expression();
				setState(1000);
				match(CLOSE_BRACE);
				}
				break;
			case MIN:
				enterOuterAlt(_localctx, 3);
				{
				setState(1002);
				match(MIN);
				setState(1003);
				match(OPEN_BRACE);
				setState(1004);
				expression();
				setState(1005);
				match(CLOSE_BRACE);
				}
				break;
			case MAX:
				enterOuterAlt(_localctx, 4);
				{
				setState(1007);
				match(MAX);
				setState(1008);
				match(OPEN_BRACE);
				setState(1009);
				expression();
				setState(1010);
				match(CLOSE_BRACE);
				}
				break;
			case AVG:
				enterOuterAlt(_localctx, 5);
				{
				setState(1012);
				match(AVG);
				setState(1013);
				match(OPEN_BRACE);
				setState(1014);
				expression();
				setState(1015);
				match(CLOSE_BRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IriRefOrFunctionContext extends ParserRuleContext {
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public IriRefOrFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iriRefOrFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterIriRefOrFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitIriRefOrFunction(this);
		}
	}

	public final IriRefOrFunctionContext iriRefOrFunction() throws RecognitionException {
		IriRefOrFunctionContext _localctx = new IriRefOrFunctionContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_iriRefOrFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1019);
			iriRef();
			setState(1021);
			_la = _input.LA(1);
			if (_la==NIL || _la==OPEN_BRACE) {
				{
				setState(1020);
				argList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RdfLiteralContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode LANGTAG() { return getToken(SparqlParser.LANGTAG, 0); }
		public TerminalNode REFERENCE() { return getToken(SparqlParser.REFERENCE, 0); }
		public IriRefContext iriRef() {
			return getRuleContext(IriRefContext.class,0);
		}
		public RdfLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rdfLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterRdfLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitRdfLiteral(this);
		}
	}

	public final RdfLiteralContext rdfLiteral() throws RecognitionException {
		RdfLiteralContext _localctx = new RdfLiteralContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_rdfLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1023);
			string();
			setState(1027);
			switch (_input.LA(1)) {
			case LANGTAG:
				{
				setState(1024);
				match(LANGTAG);
				}
				break;
			case REFERENCE:
				{
				{
				setState(1025);
				match(REFERENCE);
				setState(1026);
				iriRef();
				}
				}
				break;
			case T__0:
			case INT:
			case EXISTS:
			case UNSAID:
			case NOT:
			case OPTIONAL:
			case GRAPH:
			case FILTER:
			case A:
			case AS:
			case TRUE:
			case FALSE:
			case IRI_REF:
			case PNAME_NS:
			case PNAME_LN:
			case VAR1:
			case VAR2:
			case DECIMAL:
			case DOUBLE:
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
			case STRING_LITERAL1:
			case STRING_LITERAL2:
			case STRING_LITERAL_LONG1:
			case STRING_LITERAL_LONG2:
			case NIL:
			case ANON:
			case BLANK_NODE_LABEL:
			case AND:
			case OR:
			case OPEN_CURLY_BRACE:
			case CLOSE_CURLY_BRACE:
			case SEMICOLON:
			case DOT:
			case PLUS:
			case MINUS:
			case ASTERISK:
			case COMMA:
			case DIVIDE:
			case EQUAL:
			case LESS:
			case GREATER:
			case OPEN_BRACE:
			case CLOSE_BRACE:
			case LESS_EQUAL:
			case GREATER_EQUAL:
			case NOT_EQUAL:
			case OPEN_SQUARE_BRACE:
			case CLOSE_SQUARE_BRACE:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralContext extends ParserRuleContext {
		public NumericLiteralUnsignedContext numericLiteralUnsigned() {
			return getRuleContext(NumericLiteralUnsignedContext.class,0);
		}
		public NumericLiteralPositiveContext numericLiteralPositive() {
			return getRuleContext(NumericLiteralPositiveContext.class,0);
		}
		public NumericLiteralNegativeContext numericLiteralNegative() {
			return getRuleContext(NumericLiteralNegativeContext.class,0);
		}
		public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterNumericLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitNumericLiteral(this);
		}
	}

	public final NumericLiteralContext numericLiteral() throws RecognitionException {
		NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_numericLiteral);
		try {
			setState(1032);
			switch (_input.LA(1)) {
			case INT:
			case DECIMAL:
			case DOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1029);
				numericLiteralUnsigned();
				}
				break;
			case INTEGER_POSITIVE:
			case DECIMAL_POSITIVE:
			case DOUBLE_POSITIVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1030);
				numericLiteralPositive();
				}
				break;
			case INTEGER_NEGATIVE:
			case DECIMAL_NEGATIVE:
			case DOUBLE_NEGATIVE:
				enterOuterAlt(_localctx, 3);
				{
				setState(1031);
				numericLiteralNegative();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralUnsignedContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SparqlParser.INT, 0); }
		public TerminalNode DECIMAL() { return getToken(SparqlParser.DECIMAL, 0); }
		public TerminalNode DOUBLE() { return getToken(SparqlParser.DOUBLE, 0); }
		public NumericLiteralUnsignedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteralUnsigned; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterNumericLiteralUnsigned(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitNumericLiteralUnsigned(this);
		}
	}

	public final NumericLiteralUnsignedContext numericLiteralUnsigned() throws RecognitionException {
		NumericLiteralUnsignedContext _localctx = new NumericLiteralUnsignedContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_numericLiteralUnsigned);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1034);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==DECIMAL || _la==DOUBLE) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralPositiveContext extends ParserRuleContext {
		public TerminalNode INTEGER_POSITIVE() { return getToken(SparqlParser.INTEGER_POSITIVE, 0); }
		public TerminalNode DECIMAL_POSITIVE() { return getToken(SparqlParser.DECIMAL_POSITIVE, 0); }
		public TerminalNode DOUBLE_POSITIVE() { return getToken(SparqlParser.DOUBLE_POSITIVE, 0); }
		public NumericLiteralPositiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteralPositive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterNumericLiteralPositive(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitNumericLiteralPositive(this);
		}
	}

	public final NumericLiteralPositiveContext numericLiteralPositive() throws RecognitionException {
		NumericLiteralPositiveContext _localctx = new NumericLiteralPositiveContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_numericLiteralPositive);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1036);
			_la = _input.LA(1);
			if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (INTEGER_POSITIVE - 71)) | (1L << (DECIMAL_POSITIVE - 71)) | (1L << (DOUBLE_POSITIVE - 71)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericLiteralNegativeContext extends ParserRuleContext {
		public TerminalNode INTEGER_NEGATIVE() { return getToken(SparqlParser.INTEGER_NEGATIVE, 0); }
		public TerminalNode DECIMAL_NEGATIVE() { return getToken(SparqlParser.DECIMAL_NEGATIVE, 0); }
		public TerminalNode DOUBLE_NEGATIVE() { return getToken(SparqlParser.DOUBLE_NEGATIVE, 0); }
		public NumericLiteralNegativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericLiteralNegative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterNumericLiteralNegative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitNumericLiteralNegative(this);
		}
	}

	public final NumericLiteralNegativeContext numericLiteralNegative() throws RecognitionException {
		NumericLiteralNegativeContext _localctx = new NumericLiteralNegativeContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_numericLiteralNegative);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1038);
			_la = _input.LA(1);
			if ( !(((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (INTEGER_NEGATIVE - 74)) | (1L << (DECIMAL_NEGATIVE - 74)) | (1L << (DOUBLE_NEGATIVE - 74)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanLiteralContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(SparqlParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(SparqlParser.FALSE, 0); }
		public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterBooleanLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitBooleanLiteral(this);
		}
	}

	public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
		BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_booleanLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1040);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL1() { return getToken(SparqlParser.STRING_LITERAL1, 0); }
		public TerminalNode STRING_LITERAL2() { return getToken(SparqlParser.STRING_LITERAL2, 0); }
		public TerminalNode STRING_LITERAL_LONG1() { return getToken(SparqlParser.STRING_LITERAL_LONG1, 0); }
		public TerminalNode STRING_LITERAL_LONG2() { return getToken(SparqlParser.STRING_LITERAL_LONG2, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1042);
			_la = _input.LA(1);
			if ( !(((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (STRING_LITERAL1 - 77)) | (1L << (STRING_LITERAL2 - 77)) | (1L << (STRING_LITERAL_LONG1 - 77)) | (1L << (STRING_LITERAL_LONG2 - 77)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IriRefContext extends ParserRuleContext {
		public TerminalNode IRI_REF() { return getToken(SparqlParser.IRI_REF, 0); }
		public PrefixedNameContext prefixedName() {
			return getRuleContext(PrefixedNameContext.class,0);
		}
		public IriRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iriRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterIriRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitIriRef(this);
		}
	}

	public final IriRefContext iriRef() throws RecognitionException {
		IriRefContext _localctx = new IriRefContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_iriRef);
		try {
			setState(1046);
			switch (_input.LA(1)) {
			case IRI_REF:
				enterOuterAlt(_localctx, 1);
				{
				setState(1044);
				match(IRI_REF);
				}
				break;
			case PNAME_NS:
			case PNAME_LN:
				enterOuterAlt(_localctx, 2);
				{
				setState(1045);
				prefixedName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrefixedNameContext extends ParserRuleContext {
		public TerminalNode PNAME_LN() { return getToken(SparqlParser.PNAME_LN, 0); }
		public TerminalNode PNAME_NS() { return getToken(SparqlParser.PNAME_NS, 0); }
		public PrefixedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterPrefixedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitPrefixedName(this);
		}
	}

	public final PrefixedNameContext prefixedName() throws RecognitionException {
		PrefixedNameContext _localctx = new PrefixedNameContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_prefixedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1048);
			_la = _input.LA(1);
			if ( !(_la==PNAME_NS || _la==PNAME_LN) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlankNodeContext extends ParserRuleContext {
		public TerminalNode BLANK_NODE_LABEL() { return getToken(SparqlParser.BLANK_NODE_LABEL, 0); }
		public TerminalNode ANON() { return getToken(SparqlParser.ANON, 0); }
		public BlankNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blankNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).enterBlankNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SparqlListener ) ((SparqlListener)listener).exitBlankNode(this);
		}
	}

	public final BlankNodeContext blankNode() throws RecognitionException {
		BlankNodeContext _localctx = new BlankNodeContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_blankNode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1050);
			_la = _input.LA(1);
			if ( !(_la==ANON || _la==BLANK_NODE_LABEL) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3m\u041f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\u00e4\n\2\3\2\3\2"+
		"\3\3\5\3\u00e9\n\3\3\3\7\3\u00ec\n\3\f\3\16\3\u00ef\13\3\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\5\6\u00fd\n\6\3\7\3\7\7\7\u0101\n\7"+
		"\f\7\16\7\u0104\13\7\3\7\3\7\3\7\3\7\3\7\5\7\u010b\n\7\3\b\3\b\3\b\5\b"+
		"\u0110\n\b\3\t\3\t\5\t\u0114\n\t\3\t\7\t\u0117\n\t\f\t\16\t\u011a\13\t"+
		"\3\t\3\t\3\n\5\n\u011f\n\n\3\n\7\n\u0122\n\n\f\n\16\n\u0125\13\n\3\n\3"+
		"\n\5\n\u0129\n\n\3\13\3\13\3\13\5\13\u012e\n\13\3\f\3\f\5\f\u0132\n\f"+
		"\3\f\7\f\u0135\n\f\f\f\16\f\u0138\13\f\3\f\3\f\3\r\5\r\u013d\n\r\3\r\7"+
		"\r\u0140\n\r\f\r\16\r\u0143\13\r\3\r\3\r\5\r\u0147\n\r\3\16\5\16\u014a"+
		"\n\16\3\16\3\16\3\17\3\17\6\17\u0150\n\17\r\17\16\17\u0151\3\17\3\17\5"+
		"\17\u0156\n\17\3\20\3\20\5\20\u015a\n\20\3\21\3\21\5\21\u015e\n\21\3\22"+
		"\3\22\5\22\u0162\n\22\3\22\3\22\3\23\3\23\5\23\u0168\n\23\3\23\3\23\3"+
		"\24\5\24\u016d\n\24\3\24\3\24\3\25\3\25\5\25\u0173\n\25\3\25\6\25\u0176"+
		"\n\25\r\25\16\25\u0177\3\25\5\25\u017b\n\25\3\25\7\25\u017e\n\25\f\25"+
		"\16\25\u0181\13\25\3\25\3\25\3\25\3\26\3\26\3\26\7\26\u0189\n\26\f\26"+
		"\16\26\u018c\13\26\3\26\3\26\3\26\3\27\3\27\6\27\u0193\n\27\r\27\16\27"+
		"\u0194\3\27\5\27\u0198\n\27\3\27\7\27\u019b\n\27\f\27\16\27\u019e\13\27"+
		"\3\27\5\27\u01a1\n\27\3\27\3\27\3\30\3\30\7\30\u01a7\n\30\f\30\16\30\u01aa"+
		"\13\30\3\30\3\30\3\31\3\31\3\31\5\31\u01b1\n\31\3\32\3\32\3\33\3\33\3"+
		"\33\3\34\3\34\3\35\5\35\u01bb\n\35\3\35\3\35\3\36\5\36\u01c0\n\36\3\36"+
		"\5\36\u01c3\n\36\3\36\5\36\u01c6\n\36\3\36\5\36\u01c9\n\36\3\37\3\37\3"+
		"\37\6\37\u01ce\n\37\r\37\16\37\u01cf\3 \3 \3 \3 \3 \3 \5 \u01d8\n \3 "+
		"\3 \3 \5 \u01dd\n \3!\3!\6!\u01e1\n!\r!\16!\u01e2\3\"\3\"\3#\3#\5#\u01e9"+
		"\n#\3#\3#\5#\u01ed\n#\5#\u01ef\n#\3$\3$\3$\6$\u01f4\n$\r$\16$\u01f5\3"+
		"%\3%\3%\3%\5%\u01fc\n%\5%\u01fe\n%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\5(\u0209"+
		"\n(\3(\3(\3)\5)\u020e\n)\3)\3)\5)\u0212\n)\3)\5)\u0215\n)\3)\5)\u0218"+
		"\n)\7)\u021a\n)\f)\16)\u021d\13)\3*\3*\5*\u0221\n*\3*\3*\3*\3*\3*\3*\3"+
		"*\3*\5*\u022b\n*\3*\3*\6*\u022f\n*\r*\16*\u0230\5*\u0233\n*\5*\u0235\n"+
		"*\3+\3+\3+\5+\u023a\n+\5+\u023c\n+\3,\3,\3,\3,\3,\5,\u0243\n,\3-\3-\3"+
		"-\3.\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\5\60\u0252\n\60\3\60\3\60\3\61\3"+
		"\61\3\61\7\61\u0259\n\61\f\61\16\61\u025c\13\61\3\62\3\62\3\62\3\63\3"+
		"\63\3\63\5\63\u0264\n\63\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\7\65"+
		"\u026e\n\65\f\65\16\65\u0271\13\65\3\65\3\65\5\65\u0275\n\65\3\66\3\66"+
		"\5\66\u0279\n\66\3\66\3\66\3\67\3\67\3\67\5\67\u0280\n\67\5\67\u0282\n"+
		"\67\38\38\38\38\38\38\58\u028a\n8\39\39\39\39\39\39\59\u0292\n9\79\u0294"+
		"\n9\f9\169\u0297\139\3:\5:\u029a\n:\3;\3;\3;\7;\u029f\n;\f;\16;\u02a2"+
		"\13;\3<\3<\3=\3=\5=\u02a8\n=\3>\3>\3>\3>\3>\3>\5>\u02b0\n>\3?\3?\5?\u02b4"+
		"\n?\3?\3?\3?\3?\5?\u02ba\n?\3?\3?\5?\u02be\n?\7?\u02c0\n?\f?\16?\u02c3"+
		"\13?\3@\5@\u02c6\n@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3F\3F\3F\7F\u02d7"+
		"\nF\fF\16F\u02da\13F\3G\3G\5G\u02de\nG\3H\3H\3H\5H\u02e3\nH\3I\3I\3I\3"+
		"I\3I\3I\3I\3I\3I\5I\u02ee\nI\3I\5I\u02f1\nI\5I\u02f3\nI\3J\3J\3J\3J\3"+
		"J\3J\5J\u02fb\nJ\3K\3K\5K\u02ff\nK\3L\3L\3L\3L\3M\3M\6M\u0307\nM\rM\16"+
		"M\u0308\3M\3M\3N\3N\5N\u030f\nN\3O\3O\5O\u0313\nO\3P\3P\5P\u0317\nP\3"+
		"Q\3Q\3R\3R\3R\3R\3R\3R\5R\u0321\nR\3S\3S\3T\3T\3T\7T\u0328\nT\fT\16T\u032b"+
		"\13T\3U\3U\3U\7U\u0330\nU\fU\16U\u0333\13U\3V\3V\3W\3W\3W\3W\3W\3W\3W"+
		"\3W\3W\3W\3W\3W\3W\5W\u0344\nW\3X\3X\3Y\3Y\3Y\3Y\3Y\3Y\3Y\5Y\u034f\nY"+
		"\3Y\3Y\3Y\3Y\5Y\u0355\nY\7Y\u0357\nY\fY\16Y\u035a\13Y\3Z\3Z\3Z\3Z\3Z\7"+
		"Z\u0361\nZ\fZ\16Z\u0364\13Z\3[\3[\3[\3[\3[\3[\3[\5[\u036d\n[\3\\\3\\\3"+
		"\\\3\\\3\\\3\\\3\\\3\\\5\\\u0377\n\\\3]\3]\3]\3]\3^\3^\3^\3^\3^\3^\3^"+
		"\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^"+
		"\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^"+
		"\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\5^\u03c1\n^\3_\3_\3_\3_"+
		"\3`\3`\3`\3`\3`\3`\3`\5`\u03ce\n`\3`\3`\3a\3a\3a\3b\3b\3b\5b\u03d8\nb"+
		"\3b\3b\3c\3c\3c\3c\3c\3c\3c\5c\u03e3\nc\5c\u03e5\nc\3c\3c\3c\3c\3c\3c"+
		"\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\5c\u03fc\nc\3d\3d\5d\u0400"+
		"\nd\3e\3e\3e\3e\5e\u0406\ne\3f\3f\3f\5f\u040b\nf\3g\3g\3h\3h\3i\3i\3j"+
		"\3j\3k\3k\3l\3l\5l\u0419\nl\3m\3m\3n\3n\3n\2\2o\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtv"+
		"xz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094"+
		"\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac"+
		"\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4"+
		"\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\2\f"+
		"\3\2\26\27\3\2\"#\3\2CD\4\2\5\5GH\3\2IK\3\2LN\3\2<=\3\2OR\3\2AB\3\2UV"+
		"\u0462\2\u00dc\3\2\2\2\4\u00e8\3\2\2\2\6\u00f0\3\2\2\2\b\u00f3\3\2\2\2"+
		"\n\u00fc\3\2\2\2\f\u00fe\3\2\2\2\16\u010c\3\2\2\2\20\u0111\3\2\2\2\22"+
		"\u0123\3\2\2\2\24\u012a\3\2\2\2\26\u012f\3\2\2\2\30\u0141\3\2\2\2\32\u0149"+
		"\3\2\2\2\34\u014d\3\2\2\2\36\u0157\3\2\2\2 \u015d\3\2\2\2\"\u015f\3\2"+
		"\2\2$\u0165\3\2\2\2&\u016c\3\2\2\2(\u0170\3\2\2\2*\u0185\3\2\2\2,\u0190"+
		"\3\2\2\2.\u01a4\3\2\2\2\60\u01ad\3\2\2\2\62\u01b2\3\2\2\2\64\u01b4\3\2"+
		"\2\2\66\u01b7\3\2\2\28\u01ba\3\2\2\2:\u01bf\3\2\2\2<\u01ca\3\2\2\2>\u01dc"+
		"\3\2\2\2@\u01de\3\2\2\2B\u01e4\3\2\2\2D\u01ee\3\2\2\2F\u01f0\3\2\2\2H"+
		"\u01fd\3\2\2\2J\u01ff\3\2\2\2L\u0202\3\2\2\2N\u0205\3\2\2\2P\u020d\3\2"+
		"\2\2R\u021e\3\2\2\2T\u0236\3\2\2\2V\u0242\3\2\2\2X\u0244\3\2\2\2Z\u0247"+
		"\3\2\2\2\\\u024b\3\2\2\2^\u0251\3\2\2\2`\u0255\3\2\2\2b\u025d\3\2\2\2"+
		"d\u0263\3\2\2\2f\u0265\3\2\2\2h\u0274\3\2\2\2j\u0276\3\2\2\2l\u027c\3"+
		"\2\2\2n\u0289\3\2\2\2p\u028b\3\2\2\2r\u0299\3\2\2\2t\u029b\3\2\2\2v\u02a3"+
		"\3\2\2\2x\u02a7\3\2\2\2z\u02af\3\2\2\2|\u02b3\3\2\2\2~\u02c5\3\2\2\2\u0080"+
		"\u02c7\3\2\2\2\u0082\u02c9\3\2\2\2\u0084\u02cb\3\2\2\2\u0086\u02cd\3\2"+
		"\2\2\u0088\u02cf\3\2\2\2\u008a\u02d1\3\2\2\2\u008c\u02db\3\2\2\2\u008e"+
		"\u02e2\3\2\2\2\u0090\u02f2\3\2\2\2\u0092\u02fa\3\2\2\2\u0094\u02fe\3\2"+
		"\2\2\u0096\u0300\3\2\2\2\u0098\u0304\3\2\2\2\u009a\u030e\3\2\2\2\u009c"+
		"\u0312\3\2\2\2\u009e\u0316\3\2\2\2\u00a0\u0318\3\2\2\2\u00a2\u0320\3\2"+
		"\2\2\u00a4\u0322\3\2\2\2\u00a6\u0324\3\2\2\2\u00a8\u032c\3\2\2\2\u00aa"+
		"\u0334\3\2\2\2\u00ac\u0336\3\2\2\2\u00ae\u0345\3\2\2\2\u00b0\u0347\3\2"+
		"\2\2\u00b2\u035b\3\2\2\2\u00b4\u036c\3\2\2\2\u00b6\u0376\3\2\2\2\u00b8"+
		"\u0378\3\2\2\2\u00ba\u03c0\3\2\2\2\u00bc\u03c2\3\2\2\2\u00be\u03c6\3\2"+
		"\2\2\u00c0\u03d1\3\2\2\2\u00c2\u03d7\3\2\2\2\u00c4\u03fb\3\2\2\2\u00c6"+
		"\u03fd\3\2\2\2\u00c8\u0401\3\2\2\2\u00ca\u040a\3\2\2\2\u00cc\u040c\3\2"+
		"\2\2\u00ce\u040e\3\2\2\2\u00d0\u0410\3\2\2\2\u00d2\u0412\3\2\2\2\u00d4"+
		"\u0414\3\2\2\2\u00d6\u0418\3\2\2\2\u00d8\u041a\3\2\2\2\u00da\u041c\3\2"+
		"\2\2\u00dc\u00e3\5\4\3\2\u00dd\u00e4\5(\25\2\u00de\u00e4\5*\26\2\u00df"+
		"\u00e4\5,\27\2\u00e0\u00e4\5.\30\2\u00e1\u00e4\5\n\6\2\u00e2\u00e4\5 "+
		"\21\2\u00e3\u00dd\3\2\2\2\u00e3\u00de\3\2\2\2\u00e3\u00df\3\2\2\2\u00e3"+
		"\u00e0\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2"+
		"\2\2\u00e5\u00e6\7\2\2\3\u00e6\3\3\2\2\2\u00e7\u00e9\5\6\4\2\u00e8\u00e7"+
		"\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ed\3\2\2\2\u00ea\u00ec\5\b\5\2\u00eb"+
		"\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2"+
		"\2\2\u00ee\5\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1\7\6\2\2\u00f1\u00f2"+
		"\7@\2\2\u00f2\7\3\2\2\2\u00f3\u00f4\7\7\2\2\u00f4\u00f5\7A\2\2\u00f5\u00f6"+
		"\7@\2\2\u00f6\t\3\2\2\2\u00f7\u00fd\5\f\7\2\u00f8\u00fd\5\24\13\2\u00f9"+
		"\u00fd\5\16\b\2\u00fa\u00fd\5\34\17\2\u00fb\u00fd\5\36\20\2\u00fc\u00f7"+
		"\3\2\2\2\u00fc\u00f8\3\2\2\2\u00fc\u00f9\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc"+
		"\u00fb\3\2\2\2\u00fd\13\3\2\2\2\u00fe\u0102\7\b\2\2\u00ff\u0101\5\32\16"+
		"\2\u0100\u00ff\3\2\2\2\u0101\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103"+
		"\3\2\2\2\u0103\u0105\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0106\7\t\2\2\u0106"+
		"\u0107\5j\66\2\u0107\u0108\7\n\2\2\u0108\u010a\5j\66\2\u0109\u010b\5&"+
		"\24\2\u010a\u0109\3\2\2\2\u010a\u010b\3\2\2\2\u010b\r\3\2\2\2\u010c\u010f"+
		"\7\t\2\2\u010d\u0110\5\20\t\2\u010e\u0110\5\22\n\2\u010f\u010d\3\2\2\2"+
		"\u010f\u010e\3\2\2\2\u0110\17\3\2\2\2\u0111\u0118\7\13\2\2\u0112\u0114"+
		"\7\33\2\2\u0113\u0112\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2\2\2"+
		"\u0115\u0117\5\u00d6l\2\u0116\u0113\3\2\2\2\u0117\u011a\3\2\2\2\u0118"+
		"\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2\2\2\u011a\u0118\3\2"+
		"\2\2\u011b\u011c\5j\66\2\u011c\21\3\2\2\2\u011d\u011f\7\33\2\2\u011e\u011d"+
		"\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0122\5\u00d6l"+
		"\2\u0121\u011e\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124"+
		"\3\2\2\2\u0124\u0126\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u0128\5j\66\2\u0127"+
		"\u0129\5&\24\2\u0128\u0127\3\2\2\2\u0128\u0129\3\2\2\2\u0129\23\3\2\2"+
		"\2\u012a\u012d\7\n\2\2\u012b\u012e\5\26\f\2\u012c\u012e\5\30\r\2\u012d"+
		"\u012b\3\2\2\2\u012d\u012c\3\2\2\2\u012e\25\3\2\2\2\u012f\u0136\7\13\2"+
		"\2\u0130\u0132\7\f\2\2\u0131\u0130\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133"+
		"\3\2\2\2\u0133\u0135\5\u00d6l\2\u0134\u0131\3\2\2\2\u0135\u0138\3\2\2"+
		"\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0139\3\2\2\2\u0138\u0136"+
		"\3\2\2\2\u0139\u013a\5j\66\2\u013a\27\3\2\2\2\u013b\u013d\7\f\2\2\u013c"+
		"\u013b\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u0140\5\u00d6"+
		"l\2\u013f\u013c\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141"+
		"\u0142\3\2\2\2\u0142\u0144\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0146\5j"+
		"\66\2\u0145\u0147\5&\24\2\u0146\u0145\3\2\2\2\u0146\u0147\3\2\2\2\u0147"+
		"\31\3\2\2\2\u0148\u014a\7\'\2\2\u0149\u0148\3\2\2\2\u0149\u014a\3\2\2"+
		"\2\u014a\u014b\3\2\2\2\u014b\u014c\5\u00d6l\2\u014c\33\3\2\2\2\u014d\u014f"+
		"\7\r\2\2\u014e\u0150\5\u00d6l\2\u014f\u014e\3\2\2\2\u0150\u0151\3\2\2"+
		"\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0154"+
		"\7\f\2\2\u0154\u0156\5\u00d6l\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2"+
		"\2\u0156\35\3\2\2\2\u0157\u0159\7\16\2\2\u0158\u015a\5\32\16\2\u0159\u0158"+
		"\3\2\2\2\u0159\u015a\3\2\2\2\u015a\37\3\2\2\2\u015b\u015e\5\"\22\2\u015c"+
		"\u015e\5$\23\2\u015d\u015b\3\2\2\2\u015d\u015c\3\2\2\2\u015e!\3\2\2\2"+
		"\u015f\u0161\7\17\2\2\u0160\u0162\7\20\2\2\u0161\u0160\3\2\2\2\u0161\u0162"+
		"\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164\5\32\16\2\u0164#\3\2\2\2\u0165"+
		"\u0167\7\21\2\2\u0166\u0168\7\20\2\2\u0167\u0166\3\2\2\2\u0167\u0168\3"+
		"\2\2\2\u0168\u0169\3\2\2\2\u0169\u016a\5\32\16\2\u016a%\3\2\2\2\u016b"+
		"\u016d\7\35\2\2\u016c\u016b\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016e\3"+
		"\2\2\2\u016e\u016f\5N(\2\u016f\'\3\2\2\2\u0170\u0172\7\25\2\2\u0171\u0173"+
		"\t\2\2\2\u0172\u0171\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u017a\3\2\2\2\u0174"+
		"\u0176\5\u00a0Q\2\u0175\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0175"+
		"\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u017b\3\2\2\2\u0179\u017b\7`\2\2\u017a"+
		"\u0175\3\2\2\2\u017a\u0179\3\2\2\2\u017b\u017f\3\2\2\2\u017c\u017e\5\60"+
		"\31\2\u017d\u017c\3\2\2\2\u017e\u0181\3\2\2\2\u017f\u017d\3\2\2\2\u017f"+
		"\u0180\3\2\2\2\u0180\u0182\3\2\2\2\u0181\u017f\3\2\2\2\u0182\u0183\58"+
		"\35\2\u0183\u0184\5:\36\2\u0184)\3\2\2\2\u0185\u0186\7\30\2\2\u0186\u018a"+
		"\5j\66\2\u0187\u0189\5\60\31\2\u0188\u0187\3\2\2\2\u0189\u018c\3\2\2\2"+
		"\u018a\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018d\3\2\2\2\u018c\u018a"+
		"\3\2\2\2\u018d\u018e\58\35\2\u018e\u018f\5:\36\2\u018f+\3\2\2\2\u0190"+
		"\u0197\7\31\2\2\u0191\u0193\5\u009eP\2\u0192\u0191\3\2\2\2\u0193\u0194"+
		"\3\2\2\2\u0194\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0198\3\2\2\2\u0196"+
		"\u0198\7`\2\2\u0197\u0192\3\2\2\2\u0197\u0196\3\2\2\2\u0198\u019c\3\2"+
		"\2\2\u0199\u019b\5\60\31\2\u019a\u0199\3\2\2\2\u019b\u019e\3\2\2\2\u019c"+
		"\u019a\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u01a0\3\2\2\2\u019e\u019c\3\2"+
		"\2\2\u019f\u01a1\58\35\2\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1"+
		"\u01a2\3\2\2\2\u01a2\u01a3\5:\36\2\u01a3-\3\2\2\2\u01a4\u01a8\7\32\2\2"+
		"\u01a5\u01a7\5\60\31\2\u01a6\u01a5\3\2\2\2\u01a7\u01aa\3\2\2\2\u01a8\u01a6"+
		"\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01ab\3\2\2\2\u01aa\u01a8\3\2\2\2\u01ab"+
		"\u01ac\58\35\2\u01ac/\3\2\2\2\u01ad\u01b0\7\33\2\2\u01ae\u01b1\5\62\32"+
		"\2\u01af\u01b1\5\64\33\2\u01b0\u01ae\3\2\2\2\u01b0\u01af\3\2\2\2\u01b1"+
		"\61\3\2\2\2\u01b2\u01b3\5\66\34\2\u01b3\63\3\2\2\2\u01b4\u01b5\7\34\2"+
		"\2\u01b5\u01b6\5\66\34\2\u01b6\65\3\2\2\2\u01b7\u01b8\5\u00d6l\2\u01b8"+
		"\67\3\2\2\2\u01b9\u01bb\7\35\2\2\u01ba\u01b9\3\2\2\2\u01ba\u01bb\3\2\2"+
		"\2\u01bb\u01bc\3\2\2\2\u01bc\u01bd\5N(\2\u01bd9\3\2\2\2\u01be\u01c0\5"+
		"<\37\2\u01bf\u01be\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01c2\3\2\2\2\u01c1"+
		"\u01c3\5@!\2\u01c2\u01c1\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c5\3\2\2"+
		"\2\u01c4\u01c6\5F$\2\u01c5\u01c4\3\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01c8"+
		"\3\2\2\2\u01c7\u01c9\5D#\2\u01c8\u01c7\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9"+
		";\3\2\2\2\u01ca\u01cb\7\37\2\2\u01cb\u01cd\7!\2\2\u01cc\u01ce\5> \2\u01cd"+
		"\u01cc\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01cd\3\2\2\2\u01cf\u01d0\3\2"+
		"\2\2\u01d0=\3\2\2\2\u01d1\u01dd\5\u00ba^\2\u01d2\u01dd\5f\64\2\u01d3\u01d4"+
		"\7g\2\2\u01d4\u01d7\5\u00a4S\2\u01d5\u01d6\7+\2\2\u01d6\u01d8\5\u00a0"+
		"Q\2\u01d7\u01d5\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9"+
		"\u01da\7h\2\2\u01da\u01dd\3\2\2\2\u01db\u01dd\5\u00a0Q\2\u01dc\u01d1\3"+
		"\2\2\2\u01dc\u01d2\3\2\2\2\u01dc\u01d3\3\2\2\2\u01dc\u01db\3\2\2\2\u01dd"+
		"?\3\2\2\2\u01de\u01e0\7 \2\2\u01df\u01e1\5B\"\2\u01e0\u01df\3\2\2\2\u01e1"+
		"\u01e2\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3A\3\2\2\2"+
		"\u01e4\u01e5\5d\63\2\u01e5C\3\2\2\2\u01e6\u01e8\5J&\2\u01e7\u01e9\5L\'"+
		"\2\u01e8\u01e7\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01ef\3\2\2\2\u01ea\u01ec"+
		"\5L\'\2\u01eb\u01ed\5J&\2\u01ec\u01eb\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed"+
		"\u01ef\3\2\2\2\u01ee\u01e6\3\2\2\2\u01ee\u01ea\3\2\2\2\u01efE\3\2\2\2"+
		"\u01f0\u01f1\7\36\2\2\u01f1\u01f3\7!\2\2\u01f2\u01f4\5H%\2\u01f3\u01f2"+
		"\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5\u01f3\3\2\2\2\u01f5\u01f6\3\2\2\2\u01f6"+
		"G\3\2\2\2\u01f7\u01f8\t\3\2\2\u01f8\u01fe\5\u00b8]\2\u01f9\u01fc\5d\63"+
		"\2\u01fa\u01fc\5\u00a0Q\2\u01fb\u01f9\3\2\2\2\u01fb\u01fa\3\2\2\2\u01fc"+
		"\u01fe\3\2\2\2\u01fd\u01f7\3\2\2\2\u01fd\u01fb\3\2\2\2\u01feI\3\2\2\2"+
		"\u01ff\u0200\7$\2\2\u0200\u0201\7\5\2\2\u0201K\3\2\2\2\u0202\u0203\7%"+
		"\2\2\u0203\u0204\7\5\2\2\u0204M\3\2\2\2\u0205\u0208\7Z\2\2\u0206\u0209"+
		"\5\u00bc_\2\u0207\u0209\5P)\2\u0208\u0206\3\2\2\2\u0208\u0207\3\2\2\2"+
		"\u0209\u020a\3\2\2\2\u020a\u020b\7[\2\2\u020bO\3\2\2\2\u020c\u020e\5T"+
		"+\2\u020d\u020c\3\2\2\2\u020d\u020e\3\2\2\2\u020e\u021b\3\2\2\2\u020f"+
		"\u0212\5V,\2\u0210\u0212\5b\62\2\u0211\u020f\3\2\2\2\u0211\u0210\3\2\2"+
		"\2\u0212\u0214\3\2\2\2\u0213\u0215\7]\2\2\u0214\u0213\3\2\2\2\u0214\u0215"+
		"\3\2\2\2\u0215\u0217\3\2\2\2\u0216\u0218\5T+\2\u0217\u0216\3\2\2\2\u0217"+
		"\u0218\3\2\2\2\u0218\u021a\3\2\2\2\u0219\u0211\3\2\2\2\u021a\u021d\3\2"+
		"\2\2\u021b\u0219\3\2\2\2\u021b\u021c\3\2\2\2\u021cQ\3\2\2\2\u021d\u021b"+
		"\3\2\2\2\u021e\u0220\7\25\2\2\u021f\u0221\t\2\2\2\u0220\u021f\3\2\2\2"+
		"\u0220\u0221\3\2\2\2\u0221\u0234\3\2\2\2\u0222\u0235\7`\2\2\u0223\u0233"+
		"\5\u00a0Q\2\u0224\u0233\5\u00ba^\2\u0225\u0233\5f\64\2\u0226\u0227\7g"+
		"\2\2\u0227\u022a\5\u00a4S\2\u0228\u0229\7+\2\2\u0229\u022b\5\u00a0Q\2"+
		"\u022a\u0228\3\2\2\2\u022a\u022b\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u022d"+
		"\7h\2\2\u022d\u022f\3\2\2\2\u022e\u0226\3\2\2\2\u022f\u0230\3\2\2\2\u0230"+
		"\u022e\3\2\2\2\u0230\u0231\3\2\2\2\u0231\u0233\3\2\2\2\u0232\u0223\3\2"+
		"\2\2\u0232\u0224\3\2\2\2\u0232\u0225\3\2\2\2\u0232\u022e\3\2\2\2\u0233"+
		"\u0235\3\2\2\2\u0234\u0222\3\2\2\2\u0234\u0232\3\2\2\2\u0235S\3\2\2\2"+
		"\u0236\u023b\5z>\2\u0237\u0239\7]\2\2\u0238\u023a\5T+\2\u0239\u0238\3"+
		"\2\2\2\u0239\u023a\3\2\2\2\u023a\u023c\3\2\2\2\u023b\u0237\3\2\2\2\u023b"+
		"\u023c\3\2\2\2\u023cU\3\2\2\2\u023d\u0243\5X-\2\u023e\u0243\5`\61\2\u023f"+
		"\u0243\5Z.\2\u0240\u0243\5\\/\2\u0241\u0243\5^\60\2\u0242\u023d\3\2\2"+
		"\2\u0242\u023e\3\2\2\2\u0242\u023f\3\2\2\2\u0242\u0240\3\2\2\2\u0242\u0241"+
		"\3\2\2\2\u0243W\3\2\2\2\u0244\u0245\7&\2\2\u0245\u0246\5N(\2\u0246Y\3"+
		"\2\2\2\u0247\u0248\7\'\2\2\u0248\u0249\5\u009eP\2\u0249\u024a\5N(\2\u024a"+
		"[\3\2\2\2\u024b\u024c\7\22\2\2\u024c\u024d\5N(\2\u024d]\3\2\2\2\u024e"+
		"\u0252\7\23\2\2\u024f\u0250\7\24\2\2\u0250\u0252\7\22\2\2\u0251\u024e"+
		"\3\2\2\2\u0251\u024f\3\2\2\2\u0252\u0253\3\2\2\2\u0253\u0254\5N(\2\u0254"+
		"_\3\2\2\2\u0255\u025a\5N(\2\u0256\u0257\7(\2\2\u0257\u0259\5N(\2\u0258"+
		"\u0256\3\2\2\2\u0259\u025c\3\2\2\2\u025a\u0258\3\2\2\2\u025a\u025b\3\2"+
		"\2\2\u025ba\3\2\2\2\u025c\u025a\3\2\2\2\u025d\u025e\7)\2\2\u025e\u025f"+
		"\5d\63\2\u025fc\3\2\2\2\u0260\u0264\5\u00b8]\2\u0261\u0264\5\u00ba^\2"+
		"\u0262\u0264\5f\64\2\u0263\u0260\3\2\2\2\u0263\u0261\3\2\2\2\u0263\u0262"+
		"\3\2\2\2\u0264e\3\2\2\2\u0265\u0266\5\u00d6l\2\u0266\u0267\5h\65\2\u0267"+
		"g\3\2\2\2\u0268\u0275\7S\2\2\u0269\u026a\7g\2\2\u026a\u026f\5\u00a4S\2"+
		"\u026b\u026c\7a\2\2\u026c\u026e\5\u00a4S\2\u026d\u026b\3\2\2\2\u026e\u0271"+
		"\3\2\2\2\u026f\u026d\3\2\2\2\u026f\u0270\3\2\2\2\u0270\u0272\3\2\2\2\u0271"+
		"\u026f\3\2\2\2\u0272\u0273\7h\2\2\u0273\u0275\3\2\2\2\u0274\u0268\3\2"+
		"\2\2\u0274\u0269\3\2\2\2\u0275i\3\2\2\2\u0276\u0278\7Z\2\2\u0277\u0279"+
		"\5l\67\2\u0278\u0277\3\2\2\2\u0278\u0279\3\2\2\2\u0279\u027a\3\2\2\2\u027a"+
		"\u027b\7[\2\2\u027bk\3\2\2\2\u027c\u0281\5n8\2\u027d\u027f\7]\2\2\u027e"+
		"\u0280\5l\67\2\u027f\u027e\3\2\2\2\u027f\u0280\3\2\2\2\u0280\u0282\3\2"+
		"\2\2\u0281\u027d\3\2\2\2\u0281\u0282\3\2\2\2\u0282m\3\2\2\2\u0283\u0284"+
		"\5\u009cO\2\u0284\u0285\5p9\2\u0285\u028a\3\2\2\2\u0286\u0287\5\u0094"+
		"K\2\u0287\u0288\5r:\2\u0288\u028a\3\2\2\2\u0289\u0283\3\2\2\2\u0289\u0286"+
		"\3\2\2\2\u028ao\3\2\2\2\u028b\u028c\5x=\2\u028c\u0295\5t;\2\u028d\u0291"+
		"\7\\\2\2\u028e\u028f\5x=\2\u028f\u0290\5t;\2\u0290\u0292\3\2\2\2\u0291"+
		"\u028e\3\2\2\2\u0291\u0292\3\2\2\2\u0292\u0294\3\2\2\2\u0293\u028d\3\2"+
		"\2\2\u0294\u0297\3\2\2\2\u0295\u0293\3\2\2\2\u0295\u0296\3\2\2\2\u0296"+
		"q\3\2\2\2\u0297\u0295\3\2\2\2\u0298\u029a\5p9\2\u0299\u0298\3\2\2\2\u0299"+
		"\u029a\3\2\2\2\u029as\3\2\2\2\u029b\u02a0\5v<\2\u029c\u029d\7a\2\2\u029d"+
		"\u029f\5v<\2\u029e\u029c\3\2\2\2\u029f\u02a2\3\2\2\2\u02a0\u029e\3\2\2"+
		"\2\u02a0\u02a1\3\2\2\2\u02a1u\3\2\2\2\u02a2\u02a0\3\2\2\2\u02a3\u02a4"+
		"\5\u009aN\2\u02a4w\3\2\2\2\u02a5\u02a8\5\u009eP\2\u02a6\u02a8\7*\2\2\u02a7"+
		"\u02a5\3\2\2\2\u02a7\u02a6\3\2\2\2\u02a8y\3\2\2\2\u02a9\u02aa\5\u009c"+
		"O\2\u02aa\u02ab\5|?\2\u02ab\u02b0\3\2\2\2\u02ac\u02ad\5\u0094K\2\u02ad"+
		"\u02ae\5~@\2\u02ae\u02b0\3\2\2\2\u02af\u02a9\3\2\2\2\u02af\u02ac\3\2\2"+
		"\2\u02b0{\3\2\2\2\u02b1\u02b4\5\u0080A\2\u02b2\u02b4\5\u0082B\2\u02b3"+
		"\u02b1\3\2\2\2\u02b3\u02b2\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5\u02c1\5t"+
		";\2\u02b6\u02bd\7\\\2\2\u02b7\u02ba\5\u0080A\2\u02b8\u02ba\5\u0082B\2"+
		"\u02b9\u02b7\3\2\2\2\u02b9\u02b8\3\2\2\2\u02ba\u02bb\3\2\2\2\u02bb\u02bc"+
		"\5t;\2\u02bc\u02be\3\2\2\2\u02bd\u02b9\3\2\2\2\u02bd\u02be\3\2\2\2\u02be"+
		"\u02c0\3\2\2\2\u02bf\u02b6\3\2\2\2\u02c0\u02c3\3\2\2\2\u02c1\u02bf\3\2"+
		"\2\2\u02c1\u02c2\3\2\2\2\u02c2}\3\2\2\2\u02c3\u02c1\3\2\2\2\u02c4\u02c6"+
		"\5p9\2\u02c5\u02c4\3\2\2\2\u02c5\u02c6\3\2\2\2\u02c6\177\3\2\2\2\u02c7"+
		"\u02c8\5\u0086D\2\u02c8\u0081\3\2\2\2\u02c9\u02ca\5\u00a0Q\2\u02ca\u0083"+
		"\3\2\2\2\u02cb\u02cc\5\u0086D\2\u02cc\u0085\3\2\2\2\u02cd\u02ce\5\u0088"+
		"E\2\u02ce\u0087\3\2\2\2\u02cf\u02d0\5\u008aF\2\u02d0\u0089\3\2\2\2\u02d1"+
		"\u02d8\5\u008eH\2\u02d2\u02d3\7c\2\2\u02d3\u02d7\5\u008eH\2\u02d4\u02d5"+
		"\7\3\2\2\u02d5\u02d7\5\u008cG\2\u02d6\u02d2\3\2\2\2\u02d6\u02d4\3\2\2"+
		"\2\u02d7\u02da\3\2\2\2\u02d8\u02d6\3\2\2\2\u02d8\u02d9\3\2\2\2\u02d9\u008b"+
		"\3\2\2\2\u02da\u02d8\3\2\2\2\u02db\u02dd\5\u0092J\2\u02dc\u02de\5\u0090"+
		"I\2\u02dd\u02dc\3\2\2\2\u02dd\u02de\3\2\2\2\u02de\u008d\3\2\2\2\u02df"+
		"\u02e3\5\u008cG\2\u02e0\u02e1\7\3\2\2\u02e1\u02e3\5\u008cG\2\u02e2\u02df"+
		"\3\2\2\2\u02e2\u02e0\3\2\2\2\u02e3\u008f\3\2\2\2\u02e4\u02f3\7`\2\2\u02e5"+
		"\u02f3\7\4\2\2\u02e6\u02f3\7^\2\2\u02e7\u02e8\7Z\2\2\u02e8\u02f0\7F\2"+
		"\2\u02e9\u02ed\7a\2\2\u02ea\u02ee\7[\2\2\u02eb\u02ec\7F\2\2\u02ec\u02ee"+
		"\7[\2\2\u02ed\u02ea\3\2\2\2\u02ed\u02eb\3\2\2\2\u02ee\u02f1\3\2\2\2\u02ef"+
		"\u02f1\7[\2\2\u02f0\u02e9\3\2\2\2\u02f0\u02ef\3\2\2\2\u02f1\u02f3\3\2"+
		"\2\2\u02f2\u02e4\3\2\2\2\u02f2\u02e5\3\2\2\2\u02f2\u02e6\3\2\2\2\u02f2"+
		"\u02e7\3\2\2\2\u02f3\u0091\3\2\2\2\u02f4\u02fb\5\u00d6l\2\u02f5\u02fb"+
		"\7*\2\2\u02f6\u02f7\7g\2\2\u02f7\u02f8\5\u0086D\2\u02f8\u02f9\7h\2\2\u02f9"+
		"\u02fb\3\2\2\2\u02fa\u02f4\3\2\2\2\u02fa\u02f5\3\2\2\2\u02fa\u02f6\3\2"+
		"\2\2\u02fb\u0093\3\2\2\2\u02fc\u02ff\5\u0098M\2\u02fd\u02ff\5\u0096L\2"+
		"\u02fe\u02fc\3\2\2\2\u02fe\u02fd\3\2\2\2\u02ff\u0095\3\2\2\2\u0300\u0301"+
		"\7l\2\2\u0301\u0302\5p9\2\u0302\u0303\7m\2\2\u0303\u0097\3\2\2\2\u0304"+
		"\u0306\7g\2\2\u0305\u0307\5\u009aN\2\u0306\u0305\3\2\2\2\u0307\u0308\3"+
		"\2\2\2\u0308\u0306\3\2\2\2\u0308\u0309\3\2\2\2\u0309\u030a\3\2\2\2\u030a"+
		"\u030b\7h\2\2\u030b\u0099\3\2\2\2\u030c\u030f\5\u009cO\2\u030d\u030f\5"+
		"\u0094K\2\u030e\u030c\3\2\2\2\u030e\u030d\3\2\2\2\u030f\u009b\3\2\2\2"+
		"\u0310\u0313\5\u00a0Q\2\u0311\u0313\5\u00a2R\2\u0312\u0310\3\2\2\2\u0312"+
		"\u0311\3\2\2\2\u0313\u009d\3\2\2\2\u0314\u0317\5\u00a0Q\2\u0315\u0317"+
		"\5\u00d6l\2\u0316\u0314\3\2\2\2\u0316\u0315\3\2\2\2\u0317\u009f\3\2\2"+
		"\2\u0318\u0319\t\4\2\2\u0319\u00a1\3\2\2\2\u031a\u0321\5\u00d6l\2\u031b"+
		"\u0321\5\u00c8e\2\u031c\u0321\5\u00caf\2\u031d\u0321\5\u00d2j\2\u031e"+
		"\u0321\5\u00dan\2\u031f\u0321\7S\2\2\u0320\u031a\3\2\2\2\u0320\u031b\3"+
		"\2\2\2\u0320\u031c\3\2\2\2\u0320\u031d\3\2\2\2\u0320\u031e\3\2\2\2\u0320"+
		"\u031f\3\2\2\2\u0321\u00a3\3\2\2\2\u0322\u0323\5\u00a6T\2\u0323\u00a5"+
		"\3\2\2\2\u0324\u0329\5\u00a8U\2\u0325\u0326\7Y\2\2\u0326\u0328\5\u00a8"+
		"U\2\u0327\u0325\3\2\2\2\u0328\u032b\3\2\2\2\u0329\u0327\3\2\2\2\u0329"+
		"\u032a\3\2\2\2\u032a\u00a7\3\2\2\2\u032b\u0329\3\2\2\2\u032c\u0331\5\u00aa"+
		"V\2\u032d\u032e\7X\2\2\u032e\u0330\5\u00aaV\2\u032f\u032d\3\2\2\2\u0330"+
		"\u0333\3\2\2\2\u0331\u032f\3\2\2\2\u0331\u0332\3\2\2\2\u0332\u00a9\3\2"+
		"\2\2\u0333\u0331\3\2\2\2\u0334\u0335\5\u00acW\2\u0335\u00ab\3\2\2\2\u0336"+
		"\u0343\5\u00aeX\2\u0337\u0338\7d\2\2\u0338\u0344\5\u00aeX\2\u0339\u033a"+
		"\7k\2\2\u033a\u0344\5\u00aeX\2\u033b\u033c\7e\2\2\u033c\u0344\5\u00ae"+
		"X\2\u033d\u033e\7f\2\2\u033e\u0344\5\u00aeX\2\u033f\u0340\7i\2\2\u0340"+
		"\u0344\5\u00aeX\2\u0341\u0342\7j\2\2\u0342\u0344\5\u00aeX\2\u0343\u0337"+
		"\3\2\2\2\u0343\u0339\3\2\2\2\u0343\u033b\3\2\2\2\u0343\u033d\3\2\2\2\u0343"+
		"\u033f\3\2\2\2\u0343\u0341\3\2\2\2\u0343\u0344\3\2\2\2\u0344\u00ad\3\2"+
		"\2\2\u0345\u0346\5\u00b0Y\2\u0346\u00af\3\2\2\2\u0347\u0358\5\u00b2Z\2"+
		"\u0348\u0349\7^\2\2\u0349\u0357\5\u00b2Z\2\u034a\u034b\7_\2\2\u034b\u0357"+
		"\5\u00b2Z\2\u034c\u034f\5\u00ceh\2\u034d\u034f\5\u00d0i\2\u034e\u034c"+
		"\3\2\2\2\u034e\u034d\3\2\2\2\u034f\u0354\3\2\2\2\u0350\u0351\7`\2\2\u0351"+
		"\u0355\5\u00b4[\2\u0352\u0353\7c\2\2\u0353\u0355\5\u00b4[\2\u0354\u0350"+
		"\3\2\2\2\u0354\u0352\3\2\2\2\u0354\u0355\3\2\2\2\u0355\u0357\3\2\2\2\u0356"+
		"\u0348\3\2\2\2\u0356\u034a\3\2\2\2\u0356\u034e\3\2\2\2\u0357\u035a\3\2"+
		"\2\2\u0358\u0356\3\2\2\2\u0358\u0359\3\2\2\2\u0359\u00b1\3\2\2\2\u035a"+
		"\u0358\3\2\2\2\u035b\u0362\5\u00b4[\2\u035c\u035d\7`\2\2\u035d\u0361\5"+
		"\u00b4[\2\u035e\u035f\7c\2\2\u035f\u0361\5\u00b4[\2\u0360\u035c\3\2\2"+
		"\2\u0360\u035e\3\2\2\2\u0361\u0364\3\2\2\2\u0362\u0360\3\2\2\2\u0362\u0363"+
		"\3\2\2\2\u0363\u00b3\3\2\2\2\u0364\u0362\3\2\2\2\u0365\u0366\7b\2\2\u0366"+
		"\u036d\5\u00b6\\\2\u0367\u0368\7^\2\2\u0368\u036d\5\u00b6\\\2\u0369\u036a"+
		"\7_\2\2\u036a\u036d\5\u00b6\\\2\u036b\u036d\5\u00b6\\\2\u036c\u0365\3"+
		"\2\2\2\u036c\u0367\3\2\2\2\u036c\u0369\3\2\2\2\u036c\u036b\3\2\2\2\u036d"+
		"\u00b5\3\2\2\2\u036e\u0377\5\u00b8]\2\u036f\u0377\5\u00ba^\2\u0370\u0377"+
		"\5\u00c6d\2\u0371\u0377\5\u00c8e\2\u0372\u0377\5\u00caf\2\u0373\u0377"+
		"\5\u00d2j\2\u0374\u0377\5\u00a0Q\2\u0375\u0377\5\u00c4c\2\u0376\u036e"+
		"\3\2\2\2\u0376\u036f\3\2\2\2\u0376\u0370\3\2\2\2\u0376\u0371\3\2\2\2\u0376"+
		"\u0372\3\2\2\2\u0376\u0373\3\2\2\2\u0376\u0374\3\2\2\2\u0376\u0375\3\2"+
		"\2\2\u0377\u00b7\3\2\2\2\u0378\u0379\7g\2\2\u0379\u037a\5\u00a4S\2\u037a"+
		"\u037b\7h\2\2\u037b\u00b9\3\2\2\2\u037c\u037d\7,\2\2\u037d\u037e\7g\2"+
		"\2\u037e\u037f\5\u00a4S\2\u037f\u0380\7h\2\2\u0380\u03c1\3\2\2\2\u0381"+
		"\u0382\7-\2\2\u0382\u0383\7g\2\2\u0383\u0384\5\u00a4S\2\u0384\u0385\7"+
		"h\2\2\u0385\u03c1\3\2\2\2\u0386\u0387\7.\2\2\u0387\u0388\7g\2\2\u0388"+
		"\u0389\5\u00a4S\2\u0389\u038a\7a\2\2\u038a\u038b\5\u00a4S\2\u038b\u038c"+
		"\7h\2\2\u038c\u03c1\3\2\2\2\u038d\u038e\7/\2\2\u038e\u038f\7g\2\2\u038f"+
		"\u0390\5\u00a4S\2\u0390\u0391\7h\2\2\u0391\u03c1\3\2\2\2\u0392\u0393\7"+
		"\60\2\2\u0393\u0394\7g\2\2\u0394\u0395\5\u00a0Q\2\u0395\u0396\7h\2\2\u0396"+
		"\u03c1\3\2\2\2\u0397\u0398\7?\2\2\u0398\u03c1\5h\65\2\u0399\u039a\7>\2"+
		"\2\u039a\u039b\7g\2\2\u039b\u039c\5\u00a4S\2\u039c\u039d\7a\2\2\u039d"+
		"\u039e\5\u00a4S\2\u039e\u039f\7a\2\2\u039f\u03a0\5\u00a4S\2\u03a0\u03a1"+
		"\7h\2\2\u03a1\u03c1\3\2\2\2\u03a2\u03a3\7\61\2\2\u03a3\u03a4\7g\2\2\u03a4"+
		"\u03a5\5\u00a4S\2\u03a5\u03a6\7a\2\2\u03a6\u03a7\5\u00a4S\2\u03a7\u03a8"+
		"\7h\2\2\u03a8\u03c1\3\2\2\2\u03a9\u03aa\7\62\2\2\u03aa\u03ab\7g\2\2\u03ab"+
		"\u03ac\5\u00a4S\2\u03ac\u03ad\7h\2\2\u03ad\u03c1\3\2\2\2\u03ae\u03af\7"+
		"\63\2\2\u03af\u03b0\7g\2\2\u03b0\u03b1\5\u00a4S\2\u03b1\u03b2\7h\2\2\u03b2"+
		"\u03c1\3\2\2\2\u03b3\u03b4\7\64\2\2\u03b4\u03b5\7g\2\2\u03b5\u03b6\5\u00a4"+
		"S\2\u03b6\u03b7\7h\2\2\u03b7\u03c1\3\2\2\2\u03b8\u03b9\7\65\2\2\u03b9"+
		"\u03ba\7g\2\2\u03ba\u03bb\5\u00a4S\2\u03bb\u03bc\7h\2\2\u03bc\u03c1\3"+
		"\2\2\2\u03bd\u03c1\5\u00be`\2\u03be\u03c1\5\u00c0a\2\u03bf\u03c1\5\u00c2"+
		"b\2\u03c0\u037c\3\2\2\2\u03c0\u0381\3\2\2\2\u03c0\u0386\3\2\2\2\u03c0"+
		"\u038d\3\2\2\2\u03c0\u0392\3\2\2\2\u03c0\u0397\3\2\2\2\u03c0\u0399\3\2"+
		"\2\2\u03c0\u03a2\3\2\2\2\u03c0\u03a9\3\2\2\2\u03c0\u03ae\3\2\2\2\u03c0"+
		"\u03b3\3\2\2\2\u03c0\u03b8\3\2\2\2\u03c0\u03bd\3\2\2\2\u03c0\u03be\3\2"+
		"\2\2\u03c0\u03bf\3\2\2\2\u03c1\u00bb\3\2\2\2\u03c2\u03c3\5R*\2\u03c3\u03c4"+
		"\58\35\2\u03c4\u03c5\5:\36\2\u03c5\u00bd\3\2\2\2\u03c6\u03c7\7\66\2\2"+
		"\u03c7\u03c8\7g\2\2\u03c8\u03c9\5\u00a4S\2\u03c9\u03ca\7a\2\2\u03ca\u03cd"+
		"\5\u00a4S\2\u03cb\u03cc\7a\2\2\u03cc\u03ce\5\u00a4S\2\u03cd\u03cb\3\2"+
		"\2\2\u03cd\u03ce\3\2\2\2\u03ce\u03cf\3\2\2\2\u03cf\u03d0\7h\2\2\u03d0"+
		"\u00bf\3\2\2\2\u03d1\u03d2\7\22\2\2\u03d2\u03d3\5N(\2\u03d3\u00c1\3\2"+
		"\2\2\u03d4\u03d8\7\23\2\2\u03d5\u03d6\7\24\2\2\u03d6\u03d8\7\22\2\2\u03d7"+
		"\u03d4\3\2\2\2\u03d7\u03d5\3\2\2\2\u03d8\u03d9\3\2\2\2\u03d9\u03da\5N"+
		"(\2\u03da\u00c3\3\2\2\2\u03db\u03dc\7\67\2\2\u03dc\u03e4\7g\2\2\u03dd"+
		"\u03e5\7`\2\2\u03de\u03e5\5\u00a0Q\2\u03df\u03e2\7\26\2\2\u03e0\u03e3"+
		"\7`\2\2\u03e1\u03e3\5\u00a0Q\2\u03e2\u03e0\3\2\2\2\u03e2\u03e1\3\2\2\2"+
		"\u03e3\u03e5\3\2\2\2\u03e4\u03dd\3\2\2\2\u03e4\u03de\3\2\2\2\u03e4\u03df"+
		"\3\2\2\2\u03e5\u03e6\3\2\2\2\u03e6\u03fc\7h\2\2\u03e7\u03e8\78\2\2\u03e8"+
		"\u03e9\7g\2\2\u03e9\u03ea\5\u00a4S\2\u03ea\u03eb\7h\2\2\u03eb\u03fc\3"+
		"\2\2\2\u03ec\u03ed\79\2\2\u03ed\u03ee\7g\2\2\u03ee\u03ef\5\u00a4S\2\u03ef"+
		"\u03f0\7h\2\2\u03f0\u03fc\3\2\2\2\u03f1\u03f2\7:\2\2\u03f2\u03f3\7g\2"+
		"\2\u03f3\u03f4\5\u00a4S\2\u03f4\u03f5\7h\2\2\u03f5\u03fc\3\2\2\2\u03f6"+
		"\u03f7\7;\2\2\u03f7\u03f8\7g\2\2\u03f8\u03f9\5\u00a4S\2\u03f9\u03fa\7"+
		"h\2\2\u03fa\u03fc\3\2\2\2\u03fb\u03db\3\2\2\2\u03fb\u03e7\3\2\2\2\u03fb"+
		"\u03ec\3\2\2\2\u03fb\u03f1\3\2\2\2\u03fb\u03f6\3\2\2\2\u03fc\u00c5\3\2"+
		"\2\2\u03fd\u03ff\5\u00d6l\2\u03fe\u0400\5h\65\2\u03ff\u03fe\3\2\2\2\u03ff"+
		"\u0400\3\2\2\2\u0400\u00c7\3\2\2\2\u0401\u0405\5\u00d4k\2\u0402\u0406"+
		"\7E\2\2\u0403\u0404\7W\2\2\u0404\u0406\5\u00d6l\2\u0405\u0402\3\2\2\2"+
		"\u0405\u0403\3\2\2\2\u0405\u0406\3\2\2\2\u0406\u00c9\3\2\2\2\u0407\u040b"+
		"\5\u00ccg\2\u0408\u040b\5\u00ceh\2\u0409\u040b\5\u00d0i\2\u040a\u0407"+
		"\3\2\2\2\u040a\u0408\3\2\2\2\u040a\u0409\3\2\2\2\u040b\u00cb\3\2\2\2\u040c"+
		"\u040d\t\5\2\2\u040d\u00cd\3\2\2\2\u040e\u040f\t\6\2\2\u040f\u00cf\3\2"+
		"\2\2\u0410\u0411\t\7\2\2\u0411\u00d1\3\2\2\2\u0412\u0413\t\b\2\2\u0413"+
		"\u00d3\3\2\2\2\u0414\u0415\t\t\2\2\u0415\u00d5\3\2\2\2\u0416\u0419\7@"+
		"\2\2\u0417\u0419\5\u00d8m\2\u0418\u0416\3\2\2\2\u0418\u0417\3\2\2\2\u0419"+
		"\u00d7\3\2\2\2\u041a\u041b\t\n\2\2\u041b\u00d9\3\2\2\2\u041c\u041d\t\13"+
		"\2\2\u041d\u00db\3\2\2\2{\u00e3\u00e8\u00ed\u00fc\u0102\u010a\u010f\u0113"+
		"\u0118\u011e\u0123\u0128\u012d\u0131\u0136\u013c\u0141\u0146\u0149\u0151"+
		"\u0155\u0159\u015d\u0161\u0167\u016c\u0172\u0177\u017a\u017f\u018a\u0194"+
		"\u0197\u019c\u01a0\u01a8\u01b0\u01ba\u01bf\u01c2\u01c5\u01c8\u01cf\u01d7"+
		"\u01dc\u01e2\u01e8\u01ec\u01ee\u01f5\u01fb\u01fd\u0208\u020d\u0211\u0214"+
		"\u0217\u021b\u0220\u022a\u0230\u0232\u0234\u0239\u023b\u0242\u0251\u025a"+
		"\u0263\u026f\u0274\u0278\u027f\u0281\u0289\u0291\u0295\u0299\u02a0\u02a7"+
		"\u02af\u02b3\u02b9\u02bd\u02c1\u02c5\u02d6\u02d8\u02dd\u02e2\u02ed\u02f0"+
		"\u02f2\u02fa\u02fe\u0308\u030e\u0312\u0316\u0320\u0329\u0331\u0343\u034e"+
		"\u0354\u0356\u0358\u0360\u0362\u036c\u0376\u03c0\u03cd\u03d7\u03e2\u03e4"+
		"\u03fb\u03ff\u0405\u040a\u0418";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}