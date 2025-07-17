public class Body {
    public double xxPos; //current x
    public double yyPos; //current y
    public double xxVel; //current velocity in x
    public double yyVel; //current velocity in y
    public double mass; // its mass
    public String imgFileName;

    static final double G = 6.67E-11;

    public Body(double xP , double yP , double xV , double yV , double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;

        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Body b){

        return (G * (this.mass) * b.mass)/(calcDistance(b) * calcDistance(b));
    }

    public double calcForceExertedByX(Body b){
        return (calcForceExertedBy(b) * (b.xxPos - this.xxPos))/(calcDistance(b));
    }

    public double calcForceExertedByY(Body b){
        return (calcForceExertedBy(b) * (b.yyPos - this.yyPos))/(calcDistance(b));
    }

    public double calcNetForceExertedByX(Body[] b){
        double netForce = 0;
        for (Body obj : b){
            if(this.equals(obj)) {
                continue;
            }else{
                netForce += calcForceExertedByX(obj);
            }
        }
        return netForce;
    }
    public double calcNetForceExertedByY(Body[] b){
        double netForce = 0;
        for (Body obj : b){
            if(this.equals(obj)) {
                continue;
            }else{
                netForce += calcForceExertedByY(obj);
            }
        }
        return netForce;
    }

    public void update(double dt, double fX, double fY){
        double a_net_x = fX/this.mass;
        double a_net_y = fY/this.mass;

        this.xxVel += dt * a_net_x;
        this.yyVel += dt * a_net_y;

        this.xxPos += dt * xxVel;
        this.yyPos += dt * yyVel;

    }

    public void draw(){
        String imagePath = "images/" + this.imgFileName; // 替换为你的图片路径

        // 绘制背景图片
        StdDraw.picture(this.xxPos, this.yyPos, imagePath);

        // 显示绘制内容
        StdDraw.show();
    }
}