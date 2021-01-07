public class NBody {
    public static double readRadius(String planetsTxtPath) {
        In in = new In(planetsTxtPath); // source: an edstem post
        in.readDouble(); // source: my little brother
        double radius = in.readDouble();
        return radius;
    }

    // need to implement readInt(), readDouble(), and readString() somehow
    public static Body[] readBodies(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int num = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[num]; // source: Tymon Thi
        for (int x = 0; num > x; x = x + 1) { // source Tymon Thi
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            // Body body = new Body(xP, yP, xV, yV, m, img); {source: Tymon Thi}
            bodies[x] = new Body(xP, yP, xV, yV, m, img);

        }
        return bodies;
    }
    // int[] arr = new int[3];
    // for (int i = 0; i < arr.length; i++) {
    // arr[i] = i;
    // }
    // return arr;

    // A command-line argument is an information that directly follows the
    // program's name on the command line when it is executed. To access the
    // command-line arguments inside a Java program is quite easy. They are
    // stored as strings in the String array passed to main( ).source:
    // tutorialspoint.com
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Body b : bodies){
            b.draw();

        }
        StdDraw.enableDoubleBuffering();
        double time = 0;
        
        while (time < T) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length]; 
            for (var i = 0; i < bodies.length; i = i + 1) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            } 
            for (var i = 0; i < bodies.length; i = i + 1){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body b : bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time = time + dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                        bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }
            
    }
      
}


