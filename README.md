# customeview
##自定义雷达图
###支持设置文字颜色和大小以及显示的title
![](https://github.com/hjmJhon/customeview/raw/master/Screenshots/QQ20161231-221420@2x.png)
#### useage on your layout:
```
<com.example.stronger.customeview.widget.RadarView
        android:id="@+id/radarView"
        android:layout_centerInParent="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:textColor="@color/colorAccent"
        app:textSize="20dp"/>
```
#### 已经对width或者height是wrap_content的情形做了处理，所以你不必担心适配的问题
#####tips：
如果是wrap_content的情形，默认是400dp，所以建议根据需要给出具体的width和height