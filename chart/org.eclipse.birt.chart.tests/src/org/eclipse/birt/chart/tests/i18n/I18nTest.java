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

package org.eclipse.birt.chart.tests.i18n;

import org.eclipse.birt.chart.tests.i18n.MessagesTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class I18nTest {
	public static Test suite() {

		TestSuite suite = new TestSuite("Test for org.eclipse.birt.chart.device.extension.i18n" + //$NON-NLS-1$
				", org.eclipse.birt.chart.device.svg.i18n" + //$NON-NLS-1$
				", org.eclipse.birt.chart.engine.i18n" + //$NON-NLS-1$
				", org.eclipse.birt.chart.engine.extension.i18n" + //$NON-NLS-1$
				", org.eclipse.birt.chart.reportitem.i18n");//$NON-NLS-1$
		// $JUnit-BEGIN$
		suite.addTestSuite(MessagesTest.class);

		// $JUnit-END$
		return suite;
	}

}
