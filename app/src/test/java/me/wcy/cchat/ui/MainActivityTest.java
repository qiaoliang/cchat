package me.wcy.cchat.ui;


import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.widget.Toast;
import me.wcy.cchat.R;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    /**
     * 创建Activity测试
     */
    @Test
    public void testMainActivity() {
        assertNotNull(mainActivity);
    }

    @Ignore
    public void testJump() throws Exception {
        //Activity跳转验证

        // 获取对应的Shadow类
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        // 借助Shadow类获取启动下一Activity的Intent
        Intent nextIntent = shadowActivity.getNextStartedActivity();
        // 校验Intent的正确性
        assertEquals(nextIntent.getComponent().getClassName(), LoginActivity.class.getName());
    }


// ----------------以下是学习RobolectricTest的示例代码，如果有需要，再看就来得及-----------------//

    @Ignore
    public void testToast() throws Exception {
        Toast toast = ShadowToast.getLatestToast();
        // 判断Toast尚未弹出≠
        assertNull(toast);

        toast = ShadowToast.getLatestToast();
        // 判断Toast已经弹出
        assertNotNull(toast);
        // 获取Shadow类进行验证
        ShadowToast shadowToast = Shadows.shadowOf(toast);
        assertEquals("Hello UT!", ShadowToast.getTextOfLatestToast());
    }

    @Ignore
    public void testDialog() throws Exception {
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        // 判断Dialog尚未弹出
        assertNull(dialog);

        //mDialogBtn.performClick();
        dialog = ShadowAlertDialog.getLatestAlertDialog();
        // 判断Dialog已经弹出
        assertNotNull(dialog);
        // 获取Shadow类进行验证
        ShadowAlertDialog shadowDialog = Shadows.shadowOf(dialog);
        assertEquals("Hello UT！", shadowDialog.getMessage());
    }

    @Ignore
    public void testCheckBoxState() throws Exception {

        // 验证CheckBox初始状态
        boolean checked =false;//= checkBox.isChecked();
        assertFalse(checked);

        // 点击按钮反转CheckBox状态
       // mInverseBtn.performClick();
        // 验证状态是否正确
        assertTrue(checked);

        // 点击按钮反转CheckBox状态
        //mInverseBtn.performClick();
        // 验证状态是否正确
        assertFalse(checked);
    }
    //验证Fragment
    @Ignore
    public void testFragment() {
        //SampleFragment sampleFragment = new SampleFragment();
        //添加Fragment到Activity中，会触发Fragment的onCreateView()
        //SupportFragmentTestUtil.startFragment(sampleFragment);
        //assertNotNull(sampleFragment.getView());
    }

    @Ignore
    public void testResources() {
        //访问资源文件
        Application application = RuntimeEnvironment.application;
        String appName = application.getString(R.string.app_name);
        assertEquals("AndroidUT", appName);
    }
}