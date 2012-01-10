/*******************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.birt.data.engine.api;

import org.eclipse.birt.data.engine.api.querydefn.Binding;
import org.eclipse.birt.data.engine.api.querydefn.ConditionalExpression;
import org.eclipse.birt.data.engine.api.querydefn.FilterDefinition;
import org.eclipse.birt.data.engine.api.querydefn.GroupDefinition;
import org.eclipse.birt.data.engine.api.querydefn.QueryDefinition;
import org.eclipse.birt.data.engine.api.querydefn.ScriptExpression;
import org.eclipse.birt.data.engine.api.querydefn.SortDefinition;
import org.eclipse.birt.data.engine.core.DataException;

import testutil.ConfigText;


public class NoUpdateAggrFilterTest extends APITestCase
{

	protected DataSourceInfo getDataSourceInfo( )
	{
		return new DataSourceInfo( ConfigText.getString("Api.TestNoUpdateFilter.TableName"),
				ConfigText.getString( "Api.TestNoUpdateFilter.TableSQL" ),
				ConfigText.getString( "Api.TestNoUpdateFilter.TestDataFileName" ) );
	}
	
	protected QueryDefinition createQuery( ) throws DataException
	{
		QueryDefinition query = super.newReportQuery( );
		query.addBinding( new Binding( "ID", new ScriptExpression( "dataSetRow[\"ID\"]" ) ) );
		query.addBinding( new Binding( "COUNTRY", new ScriptExpression( "dataSetRow[\"COUNTRY\"]" ) ) );
		query.addBinding( new Binding( "STATE", new ScriptExpression( "dataSetRow[\"STATE\"]" ) ) );
		query.addBinding( new Binding( "CITY", new ScriptExpression( "dataSetRow[\"CITY\"]" ) ) );
		query.addBinding( new Binding( "AMOUNT", new ScriptExpression( "dataSetRow[\"AMOUNT\"]" ) ) );
		
		GroupDefinition g1 = new GroupDefinition( "G1" );
		g1.setKeyExpression( "row[\"COUNTRY\"]" );
		query.addGroup( g1 );

		GroupDefinition g2 = new GroupDefinition( "G2" );
		g2.setKeyExpression( "row[\"STATE\"]" );
		query.addGroup( g2 );
		
		GroupDefinition g3 = new GroupDefinition( "G3" );
		g3.setKeyExpression( "row[\"CITY\"]" );
		query.addGroup( g3 );
		
		SortDefinition sort = new SortDefinition( );
		sort.setExpression( "row[\"COUNTRY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"STATE\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"CITY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		
		return query;
	}
	
	public void testRowFilter1( ) throws Exception
	{
		QueryDefinition query = createQuery( );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("dataSetRow[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		query.addBinding( e81 );
		
		FilterDefinition f1 = new FilterDefinition( new ScriptExpression( "row[\"AMOUNT\"] > 10" ), false );
		query.addFilter( f1 );
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testRowFilter2( ) throws Exception
	{
		QueryDefinition query = createQuery( );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("dataSetRow[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		query.addBinding( e81 );
		
		FilterDefinition f1 = new FilterDefinition( new ScriptExpression( "row[\"CountG3\"] > 1" ), false );
		query.addFilter( f1 );
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testRowFilter3( ) throws Exception
	{
		QueryDefinition query = createQuery( );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISTOPN" );
		e9.addAggregateOn( "G2" );
		e9.addArgument( new ScriptExpression("row[\"CountG3\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		FilterDefinition f1 = new FilterDefinition( new ScriptExpression( "row[\"TopN\"] == false" ), false );
		query.addFilter( f1 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testRowFilter4( ) throws Exception
	{
		QueryDefinition query = createQuery( );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISTOPN" );
		e9.addAggregateOn( "G1" );
		e9.addArgument( new ScriptExpression("row[\"CountG3\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		FilterDefinition f1 = new FilterDefinition( new ScriptExpression( "row[\"TopN\"] == true" ), false );
		query.addFilter( f1 );
		
		FilterDefinition f2 = new FilterDefinition( new ScriptExpression( "row[\"COUNTRY\"] != \"China\"" ), false );
		query.addFilter( f2 );
		
		FilterDefinition f3 = new FilterDefinition( new ScriptExpression( "row[\"ID\"] > 15" ), false );
		query.addFilter( f3 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testRowFilter5( ) throws Exception
	{
		QueryDefinition query = createQuery( );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISTOPN" );
		e9.addAggregateOn( "G1" );
		e9.addArgument( new ScriptExpression("row[\"CountG3\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		FilterDefinition f1 = new FilterDefinition( new ScriptExpression( "row[\"TopN\"] == true" ), false );
		query.addFilter( f1 );
		
		FilterDefinition f2 = new FilterDefinition( new ScriptExpression( "row[\"COUNTRY\"] != \"China\"" ), false );
		query.addFilter( f2 );
		
		FilterDefinition f3 = new FilterDefinition( new ScriptExpression( "row[\"ID\"] > 15" ), false );
		query.addFilter( f3 );
		
		FilterDefinition f4 = new FilterDefinition( new ScriptExpression( "row[\"CountG3\"] < 1" ), false );
		query.addFilter( f4 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testGroupFilter1( ) throws Exception
	{
		QueryDefinition query = super.newReportQuery( );
		query.addBinding( new Binding( "ID", new ScriptExpression( "dataSetRow[\"ID\"]" ) ) );
		query.addBinding( new Binding( "COUNTRY", new ScriptExpression( "dataSetRow[\"COUNTRY\"]" ) ) );
		query.addBinding( new Binding( "STATE", new ScriptExpression( "dataSetRow[\"STATE\"]" ) ) );
		query.addBinding( new Binding( "CITY", new ScriptExpression( "dataSetRow[\"CITY\"]" ) ) );
		query.addBinding( new Binding( "AMOUNT", new ScriptExpression( "dataSetRow[\"AMOUNT\"]" ) ) );
		
		GroupDefinition g1 = new GroupDefinition( "G1" );
		g1.setKeyExpression( "row[\"COUNTRY\"]" );
		query.addGroup( g1 );
		g1.addFilter( new FilterDefinition( new ScriptExpression( "row[\"CITY\"] != \"Beijing\"" ), false ) );

		GroupDefinition g2 = new GroupDefinition( "G2" );
		g2.setKeyExpression( "row[\"STATE\"]" );
		query.addGroup( g2 );
		g2.addFilter( new FilterDefinition( new ScriptExpression( "row[\"STATE\"] != \"Scotland\"" ), false ) );
		
		GroupDefinition g3 = new GroupDefinition( "G3" );
		g3.setKeyExpression( "row[\"CITY\"]" );
		query.addGroup( g3 );
		g3.addFilter( new FilterDefinition( new ScriptExpression( "row[\"CITY\"] != \"Los Angeles\"" ), false ) );
		g3.addFilter( new FilterDefinition( new ScriptExpression( "row[\"CITY\"] != \"Washington\"" ), false ) );
		
		SortDefinition sort = new SortDefinition( );
		sort.setExpression( "row[\"COUNTRY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"STATE\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"CITY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISTOPN" );
		e9.addAggregateOn( "G1" );
		e9.addArgument( new ScriptExpression("row[\"CountG3\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testGroupFilter2( ) throws Exception
	{
		QueryDefinition query = super.newReportQuery( );
		query.addBinding( new Binding( "ID", new ScriptExpression( "dataSetRow[\"ID\"]" ) ) );
		query.addBinding( new Binding( "COUNTRY", new ScriptExpression( "dataSetRow[\"COUNTRY\"]" ) ) );
		query.addBinding( new Binding( "STATE", new ScriptExpression( "dataSetRow[\"STATE\"]" ) ) );
		query.addBinding( new Binding( "CITY", new ScriptExpression( "dataSetRow[\"CITY\"]" ) ) );
		query.addBinding( new Binding( "AMOUNT", new ScriptExpression( "dataSetRow[\"AMOUNT\"]" ) ) );
		
		GroupDefinition g1 = new GroupDefinition( "G1" );
		g1.setKeyExpression( "row[\"COUNTRY\"]" );
		query.addGroup( g1 );
		g1.addFilter( new FilterDefinition( new ScriptExpression( "row[\"COUNTRY\"] == \"China\"" ), false ) );

		GroupDefinition g2 = new GroupDefinition( "G2" );
		g2.setKeyExpression( "row[\"STATE\"]" );
		query.addGroup( g2 );
		g2.addFilter( new FilterDefinition( new ScriptExpression( "row[\"STATE\"] != \"Scotland\"" ), false ) );
		
		GroupDefinition g3 = new GroupDefinition( "G3" );
		g3.setKeyExpression( "row[\"CITY\"]" );
		query.addGroup( g3 );
		g3.addFilter( new FilterDefinition( new ScriptExpression( "row[\"CITY\"] != \"Los Angeles\"" ), false ) );
		g3.addFilter( new FilterDefinition( new ScriptExpression( "row[\"CITY\"] != \"Washington\"" ), false ) );
		
		SortDefinition sort = new SortDefinition( );
		sort.setExpression( "row[\"COUNTRY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"STATE\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"CITY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISTOPN" );
		e9.addAggregateOn( "G1" );
		e9.addArgument( new ScriptExpression("row[\"CountG3\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testGroupFilter3( ) throws Exception
	{
		QueryDefinition query = super.newReportQuery( );
		query.addBinding( new Binding( "ID", new ScriptExpression( "dataSetRow[\"ID\"]" ) ) );
		query.addBinding( new Binding( "COUNTRY", new ScriptExpression( "dataSetRow[\"COUNTRY\"]" ) ) );
		query.addBinding( new Binding( "STATE", new ScriptExpression( "dataSetRow[\"STATE\"]" ) ) );
		query.addBinding( new Binding( "CITY", new ScriptExpression( "dataSetRow[\"CITY\"]" ) ) );
		query.addBinding( new Binding( "AMOUNT", new ScriptExpression( "dataSetRow[\"AMOUNT\"]" ) ) );
		
		GroupDefinition g1 = new GroupDefinition( "G1" );
		g1.setKeyExpression( "row[\"COUNTRY\"]" );
		query.addGroup( g1 );

		GroupDefinition g2 = new GroupDefinition( "G2" );
		g2.setKeyExpression( "row[\"STATE\"]" );
		query.addGroup( g2 );
		g2.addFilter( new FilterDefinition( new ConditionalExpression( "row[\"SumAll\"]" , IConditionalExpression.OP_BOTTOM_N, "1" ), false ) );
		
		GroupDefinition g3 = new GroupDefinition( "G3" );
		g3.setKeyExpression( "row[\"CITY\"]" );
		query.addGroup( g3 );
		
		SortDefinition sort = new SortDefinition( );
		sort.setExpression( "row[\"COUNTRY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"STATE\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"CITY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		e81.addAggregateOn( "G2" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISBOTTOMN" );
		e9.addAggregateOn( "G1" );
		e9.addArgument( new ScriptExpression("row[\"SumAll\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testGroupFilter4( ) throws Exception
	{
		QueryDefinition query = super.newReportQuery( );
		query.addBinding( new Binding( "ID", new ScriptExpression( "dataSetRow[\"ID\"]" ) ) );
		query.addBinding( new Binding( "COUNTRY", new ScriptExpression( "dataSetRow[\"COUNTRY\"]" ) ) );
		query.addBinding( new Binding( "STATE", new ScriptExpression( "dataSetRow[\"STATE\"]" ) ) );
		query.addBinding( new Binding( "CITY", new ScriptExpression( "dataSetRow[\"CITY\"]" ) ) );
		query.addBinding( new Binding( "AMOUNT", new ScriptExpression( "dataSetRow[\"AMOUNT\"]" ) ) );
		
		GroupDefinition g1 = new GroupDefinition( "G1" );
		g1.setKeyExpression( "row[\"COUNTRY\"]" );
		query.addGroup( g1 );

		GroupDefinition g2 = new GroupDefinition( "G2" );
		g2.setKeyExpression( "row[\"STATE\"]" );
		query.addGroup( g2 );
		
		GroupDefinition g3 = new GroupDefinition( "G3" );
		g3.setKeyExpression( "row[\"CITY\"]" );
		query.addGroup( g3 );
		g3.addFilter( new FilterDefinition( new ScriptExpression("row[\"SumAll\"] < 53"), false) );
		
		SortDefinition sort = new SortDefinition( );
		sort.setExpression( "row[\"COUNTRY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"STATE\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"CITY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		e81.addAggregateOn( "G2" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISBOTTOMN" );
		e9.addAggregateOn( "G1" );
		e9.addArgument( new ScriptExpression("row[\"SumAll\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testGroupFilter5( ) throws Exception
	{
		QueryDefinition query = super.newReportQuery( );
		query.addBinding( new Binding( "ID", new ScriptExpression( "dataSetRow[\"ID\"]" ) ) );
		query.addBinding( new Binding( "COUNTRY", new ScriptExpression( "dataSetRow[\"COUNTRY\"]" ) ) );
		query.addBinding( new Binding( "STATE", new ScriptExpression( "dataSetRow[\"STATE\"]" ) ) );
		query.addBinding( new Binding( "CITY", new ScriptExpression( "dataSetRow[\"CITY\"]" ) ) );
		query.addBinding( new Binding( "AMOUNT", new ScriptExpression( "dataSetRow[\"AMOUNT\"]" ) ) );
		
		GroupDefinition g1 = new GroupDefinition( "G1" );
		g1.setKeyExpression( "row[\"COUNTRY\"]" );
		query.addGroup( g1 );

		GroupDefinition g2 = new GroupDefinition( "G2" );
		g2.setKeyExpression( "row[\"STATE\"]" );
		query.addGroup( g2 );
		g2.addFilter( new FilterDefinition( new ScriptExpression("row[\"TopN\"] == false"), false) );
		
		GroupDefinition g3 = new GroupDefinition( "G3" );
		g3.setKeyExpression( "row[\"CITY\"]" );
		query.addGroup( g3 );
		g3.addFilter( new FilterDefinition(new ConditionalExpression( "row[\"AMOUNT\"]" , IConditionalExpression.OP_TOP_N, "2" ), false));
		
		SortDefinition sort = new SortDefinition( );
		sort.setExpression( "row[\"COUNTRY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"STATE\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"CITY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		e81.addAggregateOn( "G1" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISTOPN" );
		e9.addArgument( new ScriptExpression("row[\"SumAll\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testGroupFilter6( ) throws Exception
	{
		QueryDefinition query = super.newReportQuery( );
		query.addBinding( new Binding( "ID", new ScriptExpression( "dataSetRow[\"ID\"]" ) ) );
		query.addBinding( new Binding( "COUNTRY", new ScriptExpression( "dataSetRow[\"COUNTRY\"]" ) ) );
		query.addBinding( new Binding( "STATE", new ScriptExpression( "dataSetRow[\"STATE\"]" ) ) );
		query.addBinding( new Binding( "CITY", new ScriptExpression( "dataSetRow[\"CITY\"]" ) ) );
		query.addBinding( new Binding( "AMOUNT", new ScriptExpression( "dataSetRow[\"AMOUNT\"]" ) ) );
		
		GroupDefinition g1 = new GroupDefinition( "G1" );
		g1.setKeyExpression( "row[\"COUNTRY\"]" );
		query.addGroup( g1 );

		GroupDefinition g2 = new GroupDefinition( "G2" );
		g2.setKeyExpression( "row[\"STATE\"]" );
		query.addGroup( g2 );
		g2.addFilter( new FilterDefinition( new ScriptExpression("row[\"TopN\"] == false"), false) );
		
		GroupDefinition g3 = new GroupDefinition( "G3" );
		g3.setKeyExpression( "row[\"CITY\"]" );
		query.addGroup( g3 );
		g3.addFilter( new FilterDefinition(new ConditionalExpression( "row[\"AMOUNT\"]" , IConditionalExpression.OP_TOP_N, "2" ), false));
		
		SortDefinition sort = new SortDefinition( );
		sort.setExpression( "row[\"COUNTRY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"STATE\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"CITY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		e81.addAggregateOn( "G1" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISTOPN" );
		e9.addArgument( new ScriptExpression("row[\"SumAll\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		FilterDefinition f1 = new FilterDefinition( new ConditionalExpression( "row[\"AMOUNT\"]" , IConditionalExpression.OP_TOP_N, "3" ), false );
		query.addFilter( f1 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
	
	public void testGroupFilter7( ) throws Exception
	{
		QueryDefinition query = super.newReportQuery( );
		query.addBinding( new Binding( "ID", new ScriptExpression( "dataSetRow[\"ID\"]" ) ) );
		query.addBinding( new Binding( "COUNTRY", new ScriptExpression( "dataSetRow[\"COUNTRY\"]" ) ) );
		query.addBinding( new Binding( "STATE", new ScriptExpression( "dataSetRow[\"STATE\"]" ) ) );
		query.addBinding( new Binding( "CITY", new ScriptExpression( "dataSetRow[\"CITY\"]" ) ) );
		query.addBinding( new Binding( "AMOUNT", new ScriptExpression( "dataSetRow[\"AMOUNT\"]" ) ) );
		
		GroupDefinition g1 = new GroupDefinition( "G1" );
		g1.setKeyExpression( "row[\"COUNTRY\"]" );
		query.addGroup( g1 );
		g1.addFilter( new FilterDefinition( new ConditionalExpression( "row[\"SumAll\"]" , IConditionalExpression.OP_TOP_N, "2" ), false) );

		GroupDefinition g2 = new GroupDefinition( "G2" );
		g2.setKeyExpression( "row[\"STATE\"]" );
		query.addGroup( g2 );
		g2.addFilter( new FilterDefinition( new ConditionalExpression( "row[\"CountG2\"]" , IConditionalExpression.OP_TOP_N, "2" ), false) );
		
		GroupDefinition g3 = new GroupDefinition( "G3" );
		g3.setKeyExpression( "row[\"CITY\"]" );
		query.addGroup( g3 );
		g3.addFilter( new FilterDefinition(new ConditionalExpression( "row[\"CountG3\"]" , IConditionalExpression.OP_TOP_N, "1" ), false));
		
		SortDefinition sort = new SortDefinition( );
		sort.setExpression( "row[\"COUNTRY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"STATE\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		sort.setExpression( "row[\"CITY\"]" );
		sort.setSortDirection( ISortDefinition.SORT_ASC );
		query.addSort( sort );
		
		IBinding e5 = new Binding( "CountG1" );
		e5.setAggrFunction( "COUNT" );
		e5.addAggregateOn( "G1" );
		query.addBinding( e5 );

		IBinding e6 = new Binding( "CountG2");
		e6.setAggrFunction( "COUNT" );
		e6.addAggregateOn( "G2" );
		query.addBinding( e6 );

		IBinding e7 = new Binding( "CountG3" );
		e7.setAggrFunction( "COUNT" );
		e7.addAggregateOn( "G3" );
		query.addBinding( e7 );
		
		IBinding e81 = new Binding( "SumAll", new ScriptExpression("row[\"AMOUNT\"]"));
		e81.setAggrFunction( "SUM" );
		e81.addAggregateOn( "G1" );
		query.addBinding( e81 );
		
		IBinding e9 = new Binding( "TopN" );
		e9.setAggrFunction( "ISTOPN" );
		e9.addArgument( new ScriptExpression("row[\"SumAll\"]") );
		e9.addArgument( new ScriptExpression("1") );
		query.addBinding( e9 );
		
		FilterDefinition f1 = new FilterDefinition( new ConditionalExpression( "row[\"AMOUNT\"]" , IConditionalExpression.OP_TOP_N, "2" ), false );
		query.addFilter( f1 );
		FilterDefinition f2 = new FilterDefinition( new ConditionalExpression( "row[\"TopN\"]" , IConditionalExpression.OP_TRUE ), false );
		query.addFilter( f2 );
		
		String[] exprs = new String[]{
				"ID", "COUNTRY", "STATE", "CITY", "AMOUNT", "CountG1", "CountG2", "CountG3", "SumAll", "TopN"
		};
		
		outputQueryResult( executeQuery( query ), exprs );
		checkOutputFile();
	}
}
