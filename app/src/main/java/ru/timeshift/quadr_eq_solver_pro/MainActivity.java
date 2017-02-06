package ru.timeshift.quadr_eq_solver_pro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
Послание автору кода:
Чувак, если ты это читаешь, то знай.
ТЫ КОНЧЕННЫЙ МУДАК, СУКА! НАХУЙ БЫЛО ГОВНОКОДИТЬ?!
А ещё пламенный привет из апреля 2015 года, уёбок.
 */

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    EditText a,b,c;
    Button solve_btn,show_sol,show_graph;
    TextView solved,roots,equation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=(EditText) findViewById(R.id.a);
        b=(EditText) findViewById(R.id.b);
        c=(EditText) findViewById(R.id.c);
        equation=(TextView) findViewById(R.id.equation);
        solved=(TextView) findViewById(R.id.solved);
        roots=(TextView) findViewById(R.id.roots);
        solve_btn=(Button) findViewById(R.id.solve_btn);
        show_sol=(Button) findViewById(R.id.show_sol);
        show_graph=(Button) findViewById(R.id.show_graph);
        solve_btn.setOnClickListener(this);
        equation.setText(Html.fromHtml("ax<sup>2</sup>+bx+c=0"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent i = new Intent(this, AboutMeActivity.class);
            startActivity(i);
            //setContentView(R.layout.activity_about_me);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        show_sol.setVisibility(View.INVISIBLE);
        show_graph.setVisibility(View.INVISIBLE);
        if (a.getText().toString() != "" & b.getText().toString() != "" & c.getText().toString() != "") {
            double av = Double.parseDouble(a.getText().toString());
            double bv = Double.parseDouble(b.getText().toString());
            double cv = Double.parseDouble(c.getText().toString());
            equation.setText(Html.fromHtml(a.getText().toString() + "x<sup>2</sup>+" + b.getText().toString() + "x+" + c.getText().toString() + "=0"));
            if (av==0) {
                solved.setVisibility(View.INVISIBLE);
                roots.setText(R.string.not_quadratic);
                return;
            }
            double x,x1,x2;
            double D = (bv * bv) - (4 * av * cv);
            x=-(bv/(2*av));
            x1=(-bv+Math.sqrt(D))/(2*av);
            x2=(-bv-Math.sqrt(D))/(2*av);
            solved.setVisibility(View.VISIBLE);
            if (D < 0)
                roots.setText(R.string.no_sol);
            else if (D == 0) roots.setText("x = "+ x );
            else roots.setText(Html.fromHtml("x<sub>1</sub> = "+x1+ "<br />" + "x<sub>2</sub> = "+ x2 ));
            show_sol.setVisibility(View.VISIBLE);
            show_graph.setVisibility(View.VISIBLE);
        }
    }

    public void showSol(View v) {
        Intent i = new Intent(this, solution.class);
        double av = Double.parseDouble(a.getText().toString());
        double bv = Double.parseDouble(b.getText().toString());
        double cv = Double.parseDouble(c.getText().toString());
        i.putExtra("a", av);
        i.putExtra("b", bv);
        i.putExtra("c", cv);
        startActivity(i);
    }
    public void showGr(View v) {
        Intent i = new Intent(this, GraphActivity.class);
        double av = Double.parseDouble(a.getText().toString());
        double bv = Double.parseDouble(b.getText().toString());
        double cv = Double.parseDouble(c.getText().toString());
        i.putExtra("a", av);
        i.putExtra("b", bv);
        i.putExtra("c", cv);
        startActivity(i);
    }
}
