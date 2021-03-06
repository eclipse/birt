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

package org.eclipse.birt.report.model.elements.interfaces;

/**
 * The interface for scalar parameter element to store the constants.
 */
public interface IScalarParameterModel {

	/**
	 * Name of the conceal-value property.
	 */

	public static final String CONCEAL_VALUE_PROP = "concealValue"; //$NON-NLS-1$

	/**
	 * Name of the allow-null property.
	 * 
	 * @deprecated by {@link #IS_REQUIRED_PROP} since BIRT 2.2
	 */

	public static final String ALLOW_NULL_PROP = "allowNull"; //$NON-NLS-1$

	/**
	 * Name of the allow-blank property.
	 * 
	 * @deprecated by {@link #IS_REQUIRED_PROP} since BIRT 2.2
	 */

	public static final String ALLOW_BLANK_PROP = "allowBlank"; //$NON-NLS-1$

	/**
	 * Name of the format property.
	 */

	public static final String FORMAT_PROP = "format"; //$NON-NLS-1$

	/**
	 * Name of the alignment property.
	 */

	public static final String ALIGNMENT_PROP = "alignment"; //$NON-NLS-1$

	/**
	 * Name of the muchMatch property for a selection list.
	 */

	public static final String MUCH_MATCH_PROP = "mustMatch"; //$NON-NLS-1$

	/**
	 * Name of the fixedOrder property for a selection list.
	 */

	public static final String FIXED_ORDER_PROP = "fixedOrder"; //$NON-NLS-1$

	/**
	 * The property name of the bound columns that bind the report element with the
	 * data set columns.
	 */

	public static final String BOUND_DATA_COLUMNS_PROP = "boundDataColumns"; //$NON-NLS-1$

	/**
	 * Name of the property that indicates the type of this parameter: simple,
	 * multi-value, or ad-hoc.
	 */

	String PARAM_TYPE_PROP = "paramType"; //$NON-NLS-1$

	/**
	 * Name of the property that gives the maximal number of of entries a report
	 * parameter pick list can have.
	 */

	public static final String AUTO_SUGGEST_THRESHOLD_PROP = "autoSuggestThreshold"; //$NON-NLS-1$

	/**
	 * Name of the method that implements to return the default value list of the
	 * parameter.
	 */
	String GET_DEFAULT_VALUE_LIST_PROP = "getDefaultValueList"; //$NON-NLS-1$

	/**
	 * Name of the method that implements to return the selection value list of the
	 * parameter. This is meaningful for 'list' or 'combo' control type parameter.
	 */
	String GET_SELECTION_VALUE_LIST_PROP = "getSelectionValueList"; //$NON-NLS-1$
}
