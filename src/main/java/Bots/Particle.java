package Bots;

import Math.PhysicsEngine;

import java.util.Arrays;

/**
 * This class is used to create an object of type Bots.Particle which will
 * be used to run the Bots.Particle Swarm optimization algorithm
 */
public class Particle {	
    double[] location;
    double[] currentDirection;
    double[] individualBest;
    double[] groupBest;
    double fitness = -999999;
    // search calibrators
    //double inertiaWeight = 0.5;
    //double indComp = 0.2;
    //double socComp = 0.3;
    double inertiaWeight = 3;
    double indComp = 1.5;
    double socComp = 1.5;
    // decreasing number through iterations
        
        public Particle() {
            this.currentDirection = randomArray();
            this.location = new double[2]
            ;
            this.location[0] = 3.329552752367831;
            this.location[1] = 1.3129591111181012;
            individualBest = Arrays.copyOf(location, 2);
            groupBest = new double[2];
            this.fitness = calcFitness();
            
            
        }

        /**
         * Sets the best Bots.Particle in the group
         * @param groupBest
         */
        public void setGroupBest(double[] groupBest) {
            this.groupBest = groupBest;
        }

        /**
         * Stores the best position of the Paricle
         * @param individualBest
         */
        public void setIndividualBest(double[] individualBest) {
            this.individualBest = individualBest;
        }
    
    
        public double[] getLocation() {
            return location;
        }
    
        public void setLocation(double[] location) {
            this.location = location;
        }
    
        public double getFitness() {
            return fitness;
        }
    
        public void setFitness(double fitness) {
            this.fitness = fitness;
        }

        /**
         * Creates a random array within the boundaries
         * @return the newly created array
         */
        public double[] randomArray(){
            double[] array = new double[2];
            for(int i = 0; i < array.length; i++){
                array[i] = Math.random()*10.0-5.0;
            }
            return array;
        }

        /**
         * Performs vector addition between two vectors
         * @param vector1
         * @param vector2
         * @return
         */
        public double[] add2Vectors(double[] vector1, double[] vector2){
            double[] sumVector = new double[2];
            for( int i = 0; i < sumVector.length; i++){
                sumVector[i] = vector1[i] + vector2[i];
            }
            return sumVector;
        }

        /**
         * Multiplies a vector by a constant 
         * @param constant
         * @param vector
         * @return
         */
        public double[] constantTimesVector(double constant, double[] vector){
            double[] resultVector = new double[2];
            for( int i = 0; i < resultVector.length; i++){
                resultVector[i] = constant * vector[i];
            }
            return resultVector; 
        }

        /**
         * Calculates new location vector for a particle
         * @param location
         * @param newVelocity
         * @return the new location vector
         */
        public double[] calcNewLocation(double[] location, double[] newVelocity){
            double[] newLocation = new double[2];
            newLocation = add2Vectors(location, newVelocity);
            return newLocation;
        }

        /**
         * Adds all vectors up and computes the new velocity
         * @param indVector individual component vector
         * @param socVector social component vector
         * @return the new velocity vector
         */
        public double[] calcNewVelocity(double[] indVector, double[] socVector){
            double[] newVelocity = new double[2];
            for( int i = 0; i < newVelocity.length; i++){
                newVelocity[i] = currentDirection[i] + indVector[i] + socVector[i];
            }
            currentDirection = Arrays.copyOf(newVelocity, 2);
            return newVelocity;
        }

        /**
         * Calculates individual component of movement
         * @return the individual component vector
         */
        public double[] calcIndVector(){
            double constant = indComp * Math.random();
            double[] indVector = new double[2];
            double[] vector = new double[2];
            vector = subtract2Vectors(individualBest, currentDirection);
            indVector = constantTimesVector(constant, vector);
            return indVector;
        }

        /**
         * Performs vector subtraction between two vectors
         * @param vector1
         * @param vector2
         * @return the resulting subtracted vector
         */
        public double[] subtract2Vectors(double[] vector1, double[] vector2){
            double[] sumVector = new double[2];
            for( int i = 0; i < sumVector.length; i++){
                sumVector[i] = vector1[i] - vector2[i];
            }
            return sumVector;
        }

        /**
         * Computes the social component of movement
         * @return the social component vector
         */
        public double[] calcSocVector(){
            double constant = socComp * Math.random();
            double[] socVector = new double[2];
            double[] vector = new double[2];
            vector = subtract2Vectors(groupBest, currentDirection);
            socVector = constantTimesVector(constant, vector);
            return socVector;
        }

        /**
         * Computes the fitness based on 100 runs of a game
         * @return average number of eliminated rows 
         */
        public double calcFitness(){
            PhysicsEngine p = new PhysicsEngine();
            double dist = p.rungeKutta4(p.x0, p.y0, location[0], location[1]);
            if (p.water == true){return -99999999;}

            return -dist;
        }

        /**
         * Checks if every parameter of the vector is in the boundary and if not 
         * it sets it randomly within the boundary
         */
        public void checkBoundary(){
            if (Math.sqrt(location[0] * location[0] + location[1] * location[1]) > 5.0){
                //TODO better boundary with bounce off
                location = randomArray();
            }        
        }
    
}
