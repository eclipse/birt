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

package org.eclipse.birt.chart.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.birt.chart.tests.device.DeviceTest;
import org.eclipse.birt.chart.tests.engine.EngineTest;
import org.eclipse.birt.chart.tests.i18n.I18nTest;
import org.eclipse.birt.chart.tests.script.SimpleAPITest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for all chart-related projects"); //$NON-NLS-1$
		// $JUnit-BEGIN$
		suite.addTest(DeviceTest.suite());
		suite.addTest(I18nTest.suite());
		suite.addTest(EngineTest.suite());
		suite.addTest(SimpleAPITest.suite());

		// $JUnit-END$
		return suite;
	}

}
