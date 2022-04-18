package Bots;

import java.util.Arrays;

/**
 * Bots.ParticleSwarm
 */
public class ParticleSwarm {
    public static void main(String[] args) throws InterruptedException {
        double decrement = 0.01;
        int population = 500;
        int iterations = 100;
        //initializing population
        Particle[] sA = new Particle[population];
        // finding the group best location
        double gBest = -999999;
        double[] gBestLoc = new double[2];
        int bestIndex = 0;
        for (int i = 0; i < population; i++){
            sA[i] = new Particle();
            sA[i].checkBoundary();
            
            if (sA[i].fitness > gBest){
                gBest = sA[i].fitness;
                bestIndex = i;
                gBestLoc = Arrays.copyOf(sA[i].getLocation(), 2);
            }
        }
        // printing group best fitness and location of group best
        // System.out.println("Best fitness: " + gBest);
        // System.out.println("Best weights: " + Arrays.toString(sA[bestIndex].getLocation()));
        // setting group best
        for (int i = 0; i < population; i++){
            sA[i].setGroupBest(sA[bestIndex].getLocation());
        }
        //iterating particle swarm set number of times
        for (int j = 0; j < iterations; j++){
            // going through the population and calculating new velocity vectors and location for each individual
            for (int g = 0; g < population; g++){
                //calculating new location
                sA[g].setLocation(sA[g].calcNewLocation(sA[g].getLocation(), sA[g].calcNewVelocity(sA[g].calcIndVector(), sA[g].calcSocVector())));
                sA[g].checkBoundary();
                //checking individual best and setting it
                double newFitness = sA[g].calcFitness();
                if (newFitness > sA[g].getFitness()){
                    sA[g].setIndividualBest(sA[g].getLocation());
                }
                sA[g].setFitness(newFitness);
                //decrementing the search factor (inertia weight) to bring the swarm to convergence
                sA[g].inertiaWeight = sA[g].inertiaWeight - decrement;
    
            }
            //checking and setting the new group best location and fitness
            for (int s = 0; s < population; s++){
                if (sA[s].fitness > gBest){
                    gBest = sA[s].fitness;
                    gBestLoc = sA[s].getLocation();
                    bestIndex = s;
                }
            }
            //printing group best location here
            System.out.println("Best location so far: " + Arrays.toString(gBestLoc) + " It's fitness: " + gBest);
        }
    }
}