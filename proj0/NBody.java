public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int planetsNum = in.readInt();
        double radius = in.readDouble();
        return radius;

    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int planetsNum = in.readInt();
        Body[] bodies =  new Body[planetsNum];

        double radius = in.readDouble();
        for (int i = 0; i < 5; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return bodies;
    }

    public static void main(String[] args){
        StdDraw.enableDoubleBuffering();
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        int time  = 0;
        Body[] bodies = readBodies(filename);
        int waitTimeMilliseconds = 10;
        StdAudio.play("audio/2001.mid");

        while(time < T){
            StdDraw.clear();
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int i = 0; i < xForces.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, "images/starfield.jpg", 2*radius, 2*radius);

            for(Body body : bodies){
                body.draw();
            }
            StdDraw.show();
            StdDraw.pause(waitTimeMilliseconds);
            time+=dt;
        }

        System.out.println(bodies.length);
        System.out.println(radius);
        for(Body body : bodies){
            System.out.println(body.xxPos + " " + body.yyPos + " " + body.xxVel + " " + body.yyVel + " " + body.mass + " " + body.imgFileName);
        }
    }
}