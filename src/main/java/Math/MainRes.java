package Math;

import Math.PhysicsEngine;

import java.io.FileNotFoundException;


public class MainRes {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        
        //Math.PhysicsEngine.EulersMethod(0.01, 0, 0, 2, 0, 0.056, 0.05);
        //Math.PhysicsEngine.SemiImplicitEulerMethod(0.01, 0, 0, 2, 0, 0.056, 0.05);
        //PrintWriter writer = new PrintWriter("exp.csv");
        //Math.PhysicsEngine.rungeKutta4(0, 0, 2, 0);
        //p.EulersMethod(stepsize, 0, 0, 2, 0, 0.056, 0.05);
        // one more 0 after i += for 100k
        
        //l.rungeKutta2(0.000000001, 0, 0, 2, 0);

        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
        PhysicsEngine d = new PhysicsEngine();
        System.out.println("Value, " + "Stepsize, " + "Odesolver");
        long time = System.currentTimeMillis();
        System.out.println(d.rungeKutta4(d.x0, d.y0, 3.3768969127158854, 1.3135526464700689));
        //double i = 0.0177827941; i< 0.10000000; i+=0.00000822172059
        for (double i = 1; i < 10000; i++) {
            
            double h = i / 100000.0; 
            h = h++;   
            
            
            //s.SemiImplicitEulerMethod(h, 0, 0, 2, 0); 
            
            //p.Euler(h, 0, 0, 2, 0);
            //g.rungeKutta2(h, 0, 0, 2, 0);
            



            // g.improvedEuler(h, 0, 0, 2, 0);
            // g.Euler(h, 0, 0, 2, 0);
            // g.SemiImplicitEulerMethod(h, 0, 0, 2, 0);
            // p.rungeKutta4(0, 0, 2, 0);




            //System.out.println(1.359157322 + ", "+ h + ", " + "Solution");
        
            
            

            
            
            // & 'C:\Program Files\Java\jdk-16.0.2\bin\java.exe' '-XX:+ShowCodeDetailsInExceptionMessages' '@C:\Users\tincu\AppData\Local\Temp\cp_4lzcd48g5ptydkaj189cwjg4j.argfile' 'Main'
        }
        //System.out.println(System.currentTimeMillis() - time);
        //public boolean Stop(double X, double Y, double h){
            //        if(Math.abs(X)-h<0 && Math.abs(Y)-h<0){
            //             return true;
            //        }
            //        return false;
            //     }
            
        
            // public boolean StopComplete(double X, double Y, double h){
            //     if((derivativeOf(X,Y,'x') ==  0) && (derivativeOf(X,Y,'y') == 0) || (Math.abs(derivativeOf(X, Y,'x'))  - h < 0) && (Math.abs(derivativeOf(X, Y,'y')) - h < 0 )){ // range
            //         return true;
            //     }
            //     else if(derivativeOf(X,Y,'x') == 0 || derivativeOf(X,Y,'y') == 0 || (Math.abs(derivativeOf(X, Y,'x'))  - h < 0) || (Math.abs(derivativeOf(X, Y,'y')) - h < 0 )){ 
            //         if(StaticFriction > Math.sqrt(Math.pow(derivativeOf(X,Y,'x'), 2) + Math.pow(derivativeOf(X,Y,'y'), 2))){
            //             return true;
            //         }
            //     }
            //     return false;
            // }
        
        
        


    }  
    
}
