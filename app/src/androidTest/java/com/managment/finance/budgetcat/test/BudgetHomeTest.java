package com.managment.finance.budgetcat.test;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;


import com.managment.finance.budgetcat.BudgetHome;
import com.managment.finance.budgetcat.R;
import com.managment.views.MapsActivity;
import com.managment.views.MapsView;
import com.managment.views.listView;

import junit.framework.TestCase;

public class BudgetHomeTest extends ActivityInstrumentationTestCase2<BudgetHome> {

    private BudgetHome budgetHome;
    private Button buttonEnter;
    private Button buttonMap ;
    private Button buttonList;
    private Button buttonSetting;
    private Button buttonQuit;
    public BudgetHomeTest(){
        super(BudgetHome.class);

    }


    public void setUp() throws Exception {
        super.setUp();
        budgetHome = getActivity();
        buttonEnter = (Button) budgetHome.findViewById(R.id.button_enter_view);
        buttonMap = (Button) budgetHome.findViewById(R.id.button_map_view);
        buttonList = (Button) budgetHome.findViewById(R.id.button_list_view);
        buttonSetting = (Button) budgetHome.findViewById(R.id.button_setting);
        buttonQuit = (Button) budgetHome.findViewById(R.id.button_quit);

    }

    public void test1() throws Exception {
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }

    public void testButtonEnter_layout() {
        final View decorView = budgetHome.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, buttonEnter);

        final ViewGroup.LayoutParams layoutParams =
                buttonEnter.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void testButtonList_layout() {
        final View decorView = budgetHome.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, buttonList);

        final ViewGroup.LayoutParams layoutParams =
                buttonList.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }
    public void testButtonMap_layout() {
        final View decorView = budgetHome.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, buttonMap);

        final ViewGroup.LayoutParams layoutParams =
                buttonMap.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }
    public void testButtonSetting_layout() {
        final View decorView = budgetHome.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView,buttonSetting );

        final ViewGroup.LayoutParams layoutParams =
                buttonSetting.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void testButtonQuit_layout() {
        final View decorView = budgetHome.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, buttonQuit);

        final ViewGroup.LayoutParams layoutParams =
                buttonQuit.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void testOpenMapActivity() {
        /// Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(MapsActivity.class.getName(),
                        null, false);

// Validate that ReceiverActivity is started
        TouchUtils.clickView(this, buttonEnter);
        MapsActivity receiverActivity = (MapsActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(5000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                MapsActivity.class, receiverActivity.getClass());

// Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);


    }

//    public void testOpenListActivity() {
//        /// Set up an ActivityMonitor
//        Instrumentation.ActivityMonitor receiverActivityMonitor2 =
//                getInstrumentation().addMonitor(listView.class.getName(),
//                        null, false);
//
//// Validate that ReceiverActivity is started
//        TouchUtils.clickView(this, buttonList);
//        listView receiverActivity = (listView)
//                receiverActivityMonitor2.waitForActivityWithTimeout(5000);
//        assertNotNull("ReceiverActivity is null", receiverActivity);
//        assertEquals("Monitor for ReceiverActivity has not been called",
//                1, receiverActivityMonitor2.getHits());
//        assertEquals("Activity is of wrong type",
//                listView.class, receiverActivity.getClass());
//
//// Remove the ActivityMonitor
//        getInstrumentation().removeMonitor(receiverActivityMonitor2);
//
//
//    }
//
//    public void testOpenMapViewActivity() {
//        /// Set up an ActivityMonitor
//        Instrumentation.ActivityMonitor receiverActivityMonitor =
//                getInstrumentation().addMonitor(MapsView.class.getName(),
//                        null, false);
//
//// Validate that ReceiverActivity is started
//        TouchUtils.clickView(this, buttonMap);
//        MapsView receiverActivity = (MapsView)
//                receiverActivityMonitor.waitForActivityWithTimeout(5000);
//        assertNotNull("ReceiverActivity is null", receiverActivity);
//        assertEquals("Monitor for ReceiverActivity has not been called",
//                1, receiverActivityMonitor.getHits());
//        assertEquals("Activity is of wrong type",
//                MapsView.class, receiverActivity.getClass());
//
//// Remove the ActivityMonitor
//        getInstrumentation().removeMonitor(receiverActivityMonitor);
//
//
//    }




//    public void tearDown() throws Exception {
//
//    }
//
//    public void testOnCreate() throws Exception {
//
//    }
//
//    public void testOnCreateOptionsMenu() throws Exception {
//
//    }
//
//    public void testOnOptionsItemSelected() throws Exception {
//
//    }


}