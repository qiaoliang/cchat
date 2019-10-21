package me.wcy.cchat.ui;

import me.wcy.cchat.BuildConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {
    LoginActivity loginActivity = null;
    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
    }
    @Test
    public void testMainActivity() {
        assertNotNull(loginActivity);
        System.out.println("ff" + "测试");
    }
}