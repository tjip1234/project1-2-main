package Math;

import Math.Derivative;
import Math.mathFunction;

public class PhysicsEngine extends mathFunction {
    public static double gravity = 9.81;
    public static double StaticFriction = 0.2;
    public static double mass = 0.056;
    public static double friction = 0.08;
    public static final double real = 1.359157322;
    public static final double xt = 4;
    public static final double yt = 1;
    public static final double r = 0.15;
    public static final double h = 0.001;
    public  final double x0 = -3;
    public  final double y0 = 0;
    public boolean water = false;

    public PhysicsEngine(){

    }
    

        

    

    public void Euler(double h, double x, double y, double vx, double vy){
        long startTime = System.nanoTime();
        State state = new State(x, y, vx, vy);
        while(true){
            State oldState = new State(x, y, vx, vy);
            x += h * vx;
            y += h * vy;
            vx += h * beginXCalculator(x,y,vx,vy);
            vy += h * beginYCalculator(x,y,vx,vy);
            
            
            if (mathFunction.Function(x,y) < 0) {
                System.out.println(Math.abs(x-real)+", " + h + ", Euler");
                break;
            }
            if (Stop(state, h)) {
                if (StopComplete(x, y,h)) {
                    //System.out.println(Math.abs(x-real) + ", " + h + ", Euler");
                    long elapsedTime = System.nanoTime() - startTime;
                    System.out.println(Math.abs(x-real) + ", " + elapsedTime + ", " + "Euler");
                    break;
                }
            }
            //SemiImplicitEulerMethod(stepsize, X,Y,velocityX,velocityY,mass,friction);
        }
    }
                       
        
    public void SemiImplicitEulerMethod(double h, double x, double y, double vx, double vy){
        long startTime = System.nanoTime();
        while(true){
            State oldState = new State(x, y, vx, vy);
            vx += h * beginXCalculator(x,y,vx,vy);
            vy += h * beginYCalculator(x,y,vx,vy);
            x += + h * vx;
            y += + h * vy;
            State state = new State(x, y, vx, vy);
            
            //System.out.println(derivativeCalculator(X, Y));
            //System.out.println(X + " " + Y + " " + velocityX + " " + velocityY );
            if (mathFunction.Function(x,y) < 0) {
                System.out.println(Math.abs(x-real)+", "+h + ", SemiImplicit");
               // System.out.println(x + ", " + h + ", semiImplicit");
                //System.out.println("fell into water");
                break;
            }
            if (stopVector(oldState, state)) {
                if (StopComplete(x, y,h)) {
                    
                    //System.out.println(Math.abs(x+0.5801521246282276)+", "+h + ", Euler");
                    //System.out.println(Math.abs(x-real)+", "+h + ", SemiImplicit");
                    //System.out.println(x + ", " + h + ", semiImplicit");
                    long elapsedTime = System.nanoTime() - startTime;
                    System.out.println(Math.abs(x-real) + ", " + elapsedTime + ", " + "SemiImplicit");

                    break;
                }
            }
            //SemiImplicitEulerMethod(stepsize, X,Y,velocityX,velocityY,mass,friction);
        }
    }





    public boolean StopRK(State state){
        
        //TODO WHEN ONLY ONE IS AT 0 SET IT TO 0



        
           if(Math.abs(state.vx) != state.vx ){ //TODO ADD vy
                return true;
           }
           return false;
        }
    

    public boolean StopComplete(double X, double Y, double h){
        // if((derivativeOf(X,Y,'x') ==  0) && (derivativeOf(X,Y,'y') == 0) || (Math.abs(derivativeOf(X, Y,'x'))  - h < 0) && (Math.abs(derivativeOf(X, Y,'y')) - h < 0 )){ // range
        //     return true;
        // }
        //if(derivativeOf(X,Y,'x') == 0 || derivativeOf(X,Y,'y') == 0 || (Math.abs(derivativeOf(X, Y,'x'))  - h < 0) || (Math.abs(derivativeOf(X, Y,'y')) - h < 0 )){ 
            if(StaticFriction > Math.sqrt(Math.pow(derivativeOf(X,Y,'x'), 2) + Math.pow(derivativeOf(X,Y,'y'), 2))){
                return true;
            }
        //}
        return false;
    }

    public double beginXCalculator(double x, double y, double vx, double vy){
        double xThing = (-gravity*derivativeOf(x,y,'x')) - (friction*gravity*vx)/(Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)));
        return xThing;
    }

    public double beginYCalculator(double positionX, double positionY, double velocityX,  double velocityY){
        double yThing = (-gravity*derivativeOf(positionX,positionY,'y')) - (friction*gravity*velocityY)/(Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2)));
        return yThing;
    }
   
    public  void rungeKutta2(double h, double x, double y, double vx, double vy) {
        //long startTime = System.nanoTime();
        State state = new State(x, y, vx, vy);
        
        while(true){
            State oldState = new State(state.x, state.y, state.vx, state.vy);
            
            
            state = integrateRK2(state, h);
            //System.out.println(x + " " + y + " " + vx + " " + vy);





            
            
            if (mathFunction.Function(state.x,state.y) < 0) {
                System.out.println(Math.abs(state.x-real)+", "+h + ", RungeKutta2");
                //System.out.println(x + ", " + h + ", RungeKutta2");
                //System.out.println("Fell into water.");
                return;
            }
            if (stopVector(oldState, state)) {
                if (StopComplete(state.x, state.y, h)) {
                    //System.out.println(x + ", " + h + ", RungeKutta2");
                    //System.out.println(Math.abs(x+0.5801521246282276) + ", " + h + ", RungeKutta2");
                    System.out.println(Math.abs(state.x-real)+", "+h + ", RungeKutta2");
                    //System.out.println(state.x+", "+h + ", RungeKutta2");
                    //System.out.println(x + ", " + h + ", RungeKutta2");
                        
                    return;
                }
            }
            
        }
    }
    public State newState(State currentStateVector, double h){
        double x = currentStateVector.x + h * currentStateVector.vx;
        double y = currentStateVector.y + h * currentStateVector.vy;
        double vx = currentStateVector.vx + h * beginXCalculator(currentStateVector.x, currentStateVector.y, currentStateVector.vx, currentStateVector.vy);
        double vy = currentStateVector.vy + h * beginYCalculator(currentStateVector.y, currentStateVector.y, currentStateVector.vx, currentStateVector.vy);
        State newState = new State(x, y, vx, vy);
        return newState;

    }

    public double derivativeOf(double x, double y, char withrespectto){
        double derivativestep = 1E-6;
        if (withrespectto == 'x'){
            return (mathFunction.Function(x+derivativestep, y)-mathFunction.Function(x, y)) / derivativestep;
        }else{
            return (mathFunction.Function(x, y+derivativestep)-mathFunction.Function(x, y)) / derivativestep;
        }
    }

    

    public  double rungeKutta4(double x, double y, double vx, double vy) {
        State state = new State(x, y, vx, vy);

        while(true){
            if (inHole(state)) {System.out.println("hit"); return 0;}
            State oldState = new State(state.x, state.y, state.vx, state.vy);
            state = integrate(state, h);           
            
            if (mathFunction.Function(state.x,state.y) < 0) {
                water = true;
                return distanceHole(state); //TODO maybe return -1 here
            }
            if (inHole(state)) {System.out.println("hit"); return 0;}
            if (Math.abs(state.vx) < h){state.vx = 0.0;}
            if (Math.abs(state.vy) < h){state.vy = 0.0;}

            
            if (Stop(state, h)) {
                if (StopComplete(state.x, state.y,h)) {
                    return distanceHole(state);}
            }
            
        }
    }    
    public Derivative evaluate(State initial, double h, Derivative d){
        State state = new State(0, 0, 0, 0);
        state.x = initial.x + d.dx*h;
        state.vx = initial.vx + d.dvx*h;

        state.y = initial.y + d.dy*h;
        state.vy = initial.vy + d.dvy*h;

        Derivative output = new Derivative(0, 0, 0, 0);
        output.dx = state.vx;
        output.dvx = accelerationX(state);

        output.dy = state.vy;
        output.dvy = accelerationY(state);
        return output;
    }

    public double accelerationX(State state){
        double x = state.x;
        double y = state.y;
        double vx = state.vx;
        double vy = state.vy;

        double xThing = (-gravity*derivativeOf(x,y,'x')) - (friction*gravity*vx)/(Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)));
        return xThing;
    }
    public double accelerationY(State state){
        double x = state.x;
        double y = state.y;
        double vx = state.vx;
        double vy = state.vy;

        double yThing = (-gravity*derivativeOf(x,y,'y')) - (friction*gravity*vy)/(Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)));
        return yThing;
    }
    
    public State integrate(State state, double h){
        Derivative a,b,c,d = new Derivative(0, 0, 0, 0);
        a = evaluate( state, 0.0, new Derivative(0, 0, 0, 0) ); // TODO do i do deriv 0
        b = evaluate( state, h*0.5, a );
        c = evaluate( state, h*0.5, b );
        d = evaluate( state, h, c );

        double dxdt = 1.0 / 6.0 * ( a.dx + 2.0 * ( b.dx + c.dx ) + d.dx );
        double dydt = 1.0 / 6.0 * ( a.dy + 2.0 * ( b.dy + c.dy ) + d.dy );
        
        double dvXdt = 1.0 / 6.0 * ( a.dvx + 2.0 * ( b.dvx + c.dvx ) + d.dvx );
        double dvYdt = 1.0 / 6.0 * ( a.dvy + 2.0 * ( b.dvy + c.dvy ) + d.dvy );

        state.x = state.x + dxdt * h;
        state.y = state.y + dydt * h;

        state.vx = state.vx + dvXdt * h;
        state.vy = state.vy + dvYdt * h;
        return state;

    }
    public State integrateRK2(State state, double h){
        Derivative a,b,c,d = new Derivative(0, 0, 0, 0);
        a = evaluate( state, 0.0, new Derivative(0, 0, 0, 0) ); // TODO do i do deriv 0
        b = evaluate( state, h*0.5, a );
        c = evaluate( state, h*0.5, b );
        d = evaluate( state, h, c );

        double dxdt = b.dx;
        double dydt = b.dy;
        
        double dvXdt = b.dvx;
        double dvYdt = b.dvy;

        state.x = state.x + dxdt * h;
        state.y = state.y + dydt * h;

        state.vx = state.vx + dvXdt * h;
        state.vy = state.vy + dvYdt * h;
        return state;

    }
    public boolean Stop(State state, double h){
        return Math.sqrt(state.vx*state.vx + state.vy * state.vy) < h;
    }
    public boolean stopVector(State old, State current){
        return old.vx * current.vx + old.vy * current.vy < 0;
    }
    public boolean overShoot(State state, double h){
        return state.vx < 0; //TODO change this, doesn't take every case lul
    }
    public boolean smallStop(State state, double h){
        double errorBound = 1E-8;
        return Math.abs(state.vx) < errorBound;
    }
    public  void fakeKut2(double h, double x, double y, double vx, double vy) {
        State state = new State(x, y, vx, vy);
        
        
        while(true){
            State oldState = new State(state.x, state.y, state.vx, state.vy);
            state = integrateRK2(state, h);           
            
            if (mathFunction.Function(state.x,state.y) < 0) {
                System.out.println("water");
                System.out.println(Math.abs(state.x-real)+", "+h + ", fakeKut2");
                return;
            }
            if (stopVector(oldState, state)) {
                // if (!smallStop(state, h)){

                //     if (overShoot(state, h)) {
                //         state = new Math.State(oldState.x, oldState.y, oldState.vx, oldState.vy);
                //         h = h / 2.0;                
                //     }
                //     h = h / 2.0;
                // }
                if (StopComplete(state.x, state.y,h)) {
                    
                    
                    //System.out.println(state.x + ", " + h + ", RungeKutta4");
                    //System.out.println(Math.abs(x+0.5801521246282276) + ", " + h + ", RungeKutta2");
                    //System.out.println(state.x+", "+h + ", RungeKutta4");
                    
                    System.out.println(Math.abs(state.x-real)+", "+h + ", fakeKut2");
                    //System.out.println(x + ", " + h + ", RungeKutta2");
                        
                    return;
                }
            }
            
        }
    }
    public  void improvedEuler(double h, double x, double y, double vx, double vy) {
        long startTime = System.nanoTime();
        
        while(true){
            State oldState = new State(x, y, vx, vy);
            State currentState = new State(x, y, vx, vy);
            State nextState = newState(currentState, h);
            x = currentState.x + h*((currentState.vx + nextState.vx) / 2.0);
            y = currentState.y + h*((currentState.vy + nextState.vy) / 2.0);

            vx = currentState.vx + h*((beginXCalculator(currentState.x, currentState.y, currentState.vx, currentState.vy) + beginXCalculator(nextState.x, nextState.y, nextState.vx, nextState.vy)) / 2.0);
            vy = currentState.vy + h*((beginYCalculator(currentState.x, currentState.y, currentState.vx, currentState.vy) + beginYCalculator(nextState.x, nextState.y, nextState.vx, nextState.vy)) / 2.0);
            
            State newState = new State(x, y, vx, vy);
            if (mathFunction.Function(x,y) < 0) {
                System.out.println(Math.abs(x-real)+", "+h + ", imprEul");
                //System.out.println("Fell into water.");
                return;
            }
            if (stopVector(oldState, newState)) {
                if (StopComplete(x, y, h)) {
                    //System.out.println(x + ", " + h + ", RungeKutta2");
                    //System.out.println(Math.abs(x+0.5801521246282276) + ", " + h + ", RungeKutta2");
                    //System.out.println(Math.abs(x-real)+", "+h + ", imprEul");
                    //System.out.println(x + ", " + h + ", RungeKutta2");
                    long elapsedTime = System.nanoTime() - startTime;
                    System.out.println(Math.abs(newState.x-real) + ", " + elapsedTime + ", " + "RK2");
                        
                    return;
                }
            }
            
        }
    }

    public boolean inHole(State state){
        return Math.abs(distanceHole(state)) < r;
    }
    public double distanceHole(State state){
        double dxSq = (state.x - xt)*(state.x - xt);
        double dySq = (state.y - yt)*(state.y - yt);
        double dzSq = (Function(state.x, state.y)-Function(xt, yt)) * (Function(state.x, state.y)-Function(xt, yt));
        return Math.sqrt(dxSq + dySq);

    }









}