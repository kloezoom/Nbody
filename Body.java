public class Body {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
        double yV, double m, String img){
            xxPos = xP;
            yyPos = yP;
            xxVel = xV;
            yyVel = yV;
            mass = m;
            imgFileName = img;
        }

    public double calcDistance(final Body b) {
        final double bx_distance = xxPos - b.xxPos;
        final double by_distance = yyPos - b.yyPos;
        return Math.sqrt(Math.pow(bx_distance, 2) + Math.pow(by_distance, 2));
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;

        }
    public double calcForceExertedBy(Body b){
        if (this.equals(b)){
            return 0;
        }
        Double G = 6.67*Math.pow(10, -11);
        return (G*mass*b.mass)/Math.pow(this.calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b){
        double bx_distance = xxPos - b.xxPos;
        return -1*calcForceExertedBy(b) * bx_distance/this.calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
        double by_distance = yyPos - b.yyPos;
        return -1*calcForceExertedBy(b) * by_distance/this.calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        double netForce = 0;
        for (int i = 0; i < allBodys.length; i = i + 1) {
            if (allBodys[i] != this){
                netForce = netForce + calcForceExertedByX(allBodys[i]);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Body[] allBodys){
        double netForce = 0;
        for (int i = 0; i < allBodys.length; i = i + 1) {
            if (allBodys[i] != this){
                netForce = netForce + calcForceExertedByY(allBodys[i]);
            }
        }
        return netForce;
    }

    public void update(double dt, double fX, double fY){
        double net_acceleration_x = fX/mass;
        double net_acceleration_y = fY/mass;
        xxVel = xxVel + dt*net_acceleration_x;
        yyVel = yyVel + dt*net_acceleration_y;
        xxPos = xxPos + dt*xxVel;
        yyPos = yyPos + dt*yyVel;
        
    }

    public void draw(){
        String imgToDraw = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgToDraw);
    }
}

