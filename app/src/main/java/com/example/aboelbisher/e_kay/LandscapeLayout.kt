package com.example.aboelbisher.e_kay

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class LandscapeLayout @JvmOverloads constructor(context: Context, attr: AttributeSet? = null, style: Int = -1) : BaseLayout(context, attr, style)
{
    init
    {
        this.orientation = LinearLayout.VERTICAL
    }
}