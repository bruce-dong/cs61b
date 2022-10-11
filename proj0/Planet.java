public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b) {
        double xDis = xxPos - b.xxPos;
        double yDis = yyPos - b.yyPos;
        double dist = Math.sqrt(xDis * xDis + yDis * yDis);
        return dist;
    }

    public double calcForceExertedBy(Planet b) {
        double dist = calcDistance(b);
        double force = (Planet.G * mass * b.mass) / (dist * dist);
        return force;
    }

    public double calcForceExertedByX(Planet b) {
        double force = calcForceExertedBy(b);
        double dist = calcDistance(b);
        double forceX = force * (b.xxPos - xxPos) / dist;
        return forceX;
    }

    public double calcForceExertedByY(Planet b) {
        double force = calcForceExertedBy(b);
        double dist = calcDistance(b);
        double forceY = force * (b.yyPos - yyPos) / dist;
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0;
        for (Planet p: planets) {
            netForceX += calcForceExertedByX(p);
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0;
        for (Planet p: planets) {
            netForceY += calcForceExertedByY(p);
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        double newVelX = xxVel + dt * aX;
        double newVelY = yyVel + dt * aY;
        double newXxPos = xxPos + newVelX * dt;
        double newYyPos = yyPos + newVelY * dt;
        xxVel = newVelX;
        yyVel = newVelY;
        xxPos = newXxPos;
        yyPos = newYyPos;
    }
}