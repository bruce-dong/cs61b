public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        final double nPlanets = 5;
        Planet[] planets = new Planet[5];
        In in = new In(fileName);
        in.readInt();
        in.readDouble();
        double xxPos, yyPos, xxVel, yyVel, mass;
        String imgName;
        for(int i = 0; i < 5; i++) {
                xxPos = in.readDouble();
                yyPos = in.readDouble();
                xxVel = in.readDouble();
                yyVel = in.readDouble();
                mass = in.readDouble();
                imgName = in.readString();
                Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgName);
                planets[i] = p;
        }
        return planets;
    }

    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);

        double radius = readRadius(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.enableDoubleBuffering();

        for (Planet p: planets) {
            p.draw();
        }

        double time = 0;
        while (time <= T) {
            double xForces[] = new double[5];
            double yForces[] = new double[5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (j != i) {
                        xForces[i] += planets[i].calcForceExertedByX(planets[j]);
                        yForces[i] += planets[i].calcForceExertedByY(planets[j]);
                    }

                }
            }

            for (int i = 0; i < 5; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p: planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;

        }
    }
}