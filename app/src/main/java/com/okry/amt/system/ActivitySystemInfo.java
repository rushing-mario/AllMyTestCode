package com.okry.amt.system;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import com.okry.amt.allbase.BaseActivity;
import com.okry.amt.log.L;

/**
 * Created by MR on 2014/4/21.
 */
public class ActivitySystemInfo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StringBuilder sb = new StringBuilder();

        int num = SystemInfo.getNumCores();
        sb.append("CPU核心数：" + num + "\n");

        String cpuAbi = SystemInfo.getCpuABI();
        sb.append("CPU ABI:" + cpuAbi + "\n");

        String[] cpuInfo = SystemInfo.getCpuInfo();
        sb.append("CPU info:\n");
        for(int i = 0; i < cpuInfo.length; i++) {
            sb.append(cpuInfo[i] + "\n");
        }

        TextView tv = new TextView(this);
        tv.setText(sb.toString());
        ScrollView container = new ScrollView(this);
        container.addView(tv);
        setContentView(container);
    }
}
