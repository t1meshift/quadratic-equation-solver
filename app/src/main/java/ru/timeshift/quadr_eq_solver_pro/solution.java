package ru.timeshift.quadr_eq_solver_pro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.text.Html;

public class solution extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);
        Intent i = getIntent();
        double a = i.getDoubleExtra("a",0.0),
        b = i.getDoubleExtra("b",0.0),
        c = i.getDoubleExtra("c",0.0),
        D = (b*b-4*a*c),
        x=-(b/(2*a)),
        x1=(-b+Math.sqrt(D))/(2*a),
        x2=(-b-Math.sqrt(D))/(2*a);
        String dano_ur = getBaseContext().getString(R.string.dano_ur),
        need2findD = getBaseContext().getString(R.string.need2findD),
        Dlt0 = getBaseContext().getString(R.string.Dlt0),
        Dis0 = getBaseContext().getString(R.string.Dis0),
        Dgt0 = getBaseContext().getString(R.string.Dgt0),
        otvet = getBaseContext().getString(R.string.otvet);
        TextView sol=(TextView) findViewById(R.id.solText);
        sol.setText(dano_ur+"<br />"+a+"x<sup>2</sup>+"+b+"x+"+c+"=0.<br /><br />"+need2findD+"\nb<sup>2</sup>-4ac<br />" +
                "("+b+")<sup>2</sup>-4*"+a+"*"+c+" = "+D+"<br /><br />");
        if (D < 0) sol.setText(sol.getText().toString()+
                Dlt0);
        else if (D==0) sol.setText(sol.getText().toString()+
                Dis0+
                " = "+(-b)+"/"+2*a+" = " + x+"<br /><br />"+
                otvet+x);
        else sol.setText(sol.getText().toString()+
                    Dgt0+"\nx<sub>1,2</sub> = (-b±√D)/(2a)<br /><br />"+
                    "x<sub>1</sub> = (-b+√D)/(2a) = ("+(-b)+"+"+Math.sqrt(D)+")/"+2*a+" = "+x1+"<br />"+
                    "x<sub>2</sub> = (-b-√D)/(2a) = ("+(-b)+"-"+Math.sqrt(D)+")/"+2*a+" = "+x2+"<br /><br />"+
                    otvet+x1+"; "+x2);
        //sol.getText().toString()
        sol.setText(Html.fromHtml(sol.getText().toString()));
    }

}
