/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.birt.data.engine.impl;

import java.util.List;
import java.util.Map;

import org.eclipse.birt.data.engine.api.IBaseExpression;
import org.eclipse.birt.data.engine.api.IBaseQueryDefinition;
import org.eclipse.birt.data.engine.api.IPreparedQuery;
import org.eclipse.birt.data.engine.api.IQueryResults;
import org.eclipse.birt.data.engine.api.IResultMetaData;
import org.eclipse.birt.data.engine.api.IScriptExpression;
import org.eclipse.birt.data.engine.core.DataException;
import org.eclipse.birt.data.engine.odi.IResultIterator;
import org.mozilla.javascript.Scriptable;

/**
 * Wrap the service which is provided for IQueryResults to make IQueryResults
 * knows only these information it needes.
 */
interface IServiceForQueryResults {
	/**
	 * @return
	 */
	public DataEngineSession getSession();

	/**
	 * @return
	 */
	public Scriptable getScope();

	/**
	 * If it is a nested query, this value indicates which level this query is
	 * placed.
	 * 
	 * @return
	 */
	public int getNestedLevel();

	/**
	 * @return base query definition
	 */
	public IBaseQueryDefinition getQueryDefn();

	/**
	 * @return
	 */
	public IPreparedQuery getPreparedQuery();

	/**
	 * If it is a sub query, this value indicates which group level this query is
	 * placed.
	 * 
	 * @return
	 */
	public int getGroupLevel();

	/**
	 * Associated data set runtime instance.
	 * 
	 * @return
	 */
	public DataSetRuntime getDataSetRuntime();

	/**
	 * Associated data sets runtime, frou inner to outer.
	 * 
	 * @param count, how many levels needs to be traced.
	 * @return
	 */
	public DataSetRuntime[] getDataSetRuntimes(int count);

	/**
	 * @return meta data of data set
	 * @throws DataException
	 */
	public IResultMetaData getResultMetaData() throws DataException;

	/**
	 * @return
	 */
	public IResultIterator executeQuery() throws DataException;

	/**
	 * @param iterator
	 * @param subQueryName
	 * @param subScope
	 * @return
	 * @throws DataException
	 */
	public IQueryResults execSubquery(IResultIterator iterator, IQueryExecutor parentQueryExecutor, String subQueryName,
			Scriptable subScope) throws DataException;

	/**
	 * close service
	 */
	public void close();

	/**
	 * @return the valid property of defined column bindings
	 * @throws DataException
	 */
	public void validateQuery() throws DataException;

	/**
	 * @param exprName
	 * @return associated defined binding expression
	 * @throws DataException
	 */
	public IBaseExpression getBindingExpr(String exprName) throws DataException;

	/**
	 * @param exprName
	 * @return associated defined auto binding expression
	 */
	public IScriptExpression getAutoBindingExpr(String exprName);

	/**
	 * @return {@link org.eclipse.birt.data.engine.impl.GroupBindingColumn}
	 */
	public List getAllBindingExprs();

	/**
	 * @return the map of <column name, associated expression>
	 */
	public Map getAllAutoBindingExprs();

	/**
	 * init auto binding at the start time
	 * 
	 * @throws DataException
	 */
	public void initAutoBinding() throws DataException;

	/**
	 * Return the query executor.
	 */
	public IQueryExecutor getQueryExecutor() throws DataException;

	/**
	 * Return the starting raw id for the query results. For ordinary query the
	 * starting raw id will always be 0. For subquery the starting raw id will be
	 * the very first row id of its parent result iterator.
	 * 
	 * @return
	 * @throws DataException
	 */
	public int getStartingRawID() throws DataException;

}
