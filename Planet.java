public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public double xNetForce;
	public double yNetForce;
  public String imgFileName;
  public double G = 6.67e-11;

public Planet(double xP, double yP, double xV, double yV, double m, String img) {
  xxPos = xP;
  yyPos = yP;
  xxVel = xV;
  yyVel = yV;
  mass = m;
  imgFileName = img;
}

public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
}

public double calcDistance(Planet p) {
  return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
}

public double calcForceExertedBy(Planet p) { //F
	return (G * p.mass * mass) / (calcDistance(p) * calcDistance(p));
}

public double calcForceExertedByX(Planet p) { //Fx
	return (calcForceExertedBy(p) * (p.xxPos - xxPos)) / calcDistance(p);
}

public double calcForceExertedByY(Planet p) { //Fy
	return (calcForceExertedBy(p) * (p.yyPos - yyPos)) / calcDistance(p);
}

public double calcNetForceExertedByX(Planet[] planets) { //FnetX
  double xNetForce = 0;
  for (Planet p : planets){
    if (this == p){
      continue;
    } else {
      xNetForce += calcForceExertedByX(p);
    }
  }
  return xNetForce;
}

public double calcNetForceExertedByY(Planet[] planets) { //FnetY
  double yNetForce = 0;
  for (Planet p : planets){
    if (this == p) {
      continue;
    } else {
      yNetForce += calcForceExertedByY(p);
    }
  }
  return yNetForce;
}

public void update(double dt, double fX, double fY) {
		xxVel = xxVel + dt * (fX / mass);
		yyVel = yyVel + dt * (fY / mass);
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

public void draw() {
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}
}
