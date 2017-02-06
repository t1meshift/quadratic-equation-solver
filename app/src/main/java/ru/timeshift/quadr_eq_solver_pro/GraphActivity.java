package ru.timeshift.quadr_eq_solver_pro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends ActionBarActivity {

    public double a,b,c;
    TextView xv_yv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Intent i = getIntent();
                a = i.getDoubleExtra("a",0.0);
                b = i.getDoubleExtra("b",0.0);
                c = i.getDoubleExtra("c",0.0);
        double xv=-(b/(2*a)),
                yv=c_yv(xv);
        xv_yv = (TextView) findViewById(R.id.xv_yv);
        String vertex = getBaseContext().getString(R.string.vertex);
        xv_yv.setText(Html.fromHtml("x<sub>"+vertex+"</sub> = "+ -b+"/2*"+a +" = "+xv+"<br />y<sub>"+vertex+"</sub> = "+a+"x<sub>"+vertex+"</sub><sup>2</sup>+"+b+"x<sub>"+vertex+"</sub>+"+c+" = "+yv));
        addRow("x","y");
         addRow(Double.toString(xv),Double.toString(yv));
        if (xv==Math.ceil(xv)) {
            addRow(Double.toString(xv + 1), Double.toString(c_yv(xv + 1) ));
            addRow(Double.toString(xv + 2), Double.toString(c_yv(xv + 2)));
            addRow(Double.toString(xv + 3), Double.toString(c_yv(xv + 3)));
        } else {
            addRow(Double.toString(Math.ceil(xv)), Double.toString(c_yv(Math.ceil(xv))));
            addRow(Double.toString(Math.ceil(xv) + 1), Double.toString(c_yv(Math.ceil(xv) + 1)));
            addRow(Double.toString(Math.ceil(xv) + 2), Double.toString(c_yv(Math.ceil(xv) + 2)));
        }
        // Построение графика
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("y="+a+"x^2+"+b+"x+"+c);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(xv-10);
        graph.getViewport().setMaxX(xv+10);

        // graph.getGridLabelRenderer().

// set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(yv-10);
        graph.getViewport().setMaxY(yv+10);
       // graph.addSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{});
        double xmin=Math.floor(xv)-5, xmax = Math.floor(xv)+5;
        for (double cnt=xmin;cnt<=xmax;cnt+=0.5){
            double yy = c_yv(cnt);
            series.appendData(new DataPoint(cnt,yy),false,1000);
           // addRow(Double.toString(cnt),Double.toString(c_yv(cnt)));
        }

        // set manual X bounds
        graph.addSeries(series);
    }

    //Классная тема, я бы сказал, что идеальная
    public void addRow(String cell0, String cell1) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
        TextView tv = (TextView) tr.getChildAt(0);
        tv.setText(cell0);
        tv = (TextView) tr.getChildAt(1);
        tv.setText(cell1);
        tableLayout.addView(tr);
    }
    public double c_yv(double x){
        return a*x*x+b*x+c;
    }
}
