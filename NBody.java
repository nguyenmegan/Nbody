public class NBody {

public static void main(String[] args) {
  double T, dt, radius;
  String fileName;
  double time = 0.0;

  T = Double.parseDouble(args[0]);
  dt = Double.parseDouble(args[1]);
  fileName = args[2];

  radius = readRadius(fileName);
  StdDraw.setScale(-radius, radius);
  Planet[] array = readPlanets(fileName);

  while (time <= T) {
    double[] xForces = new double[array.length];
    double[] yForces = new double[array.length];
    for (int i = 0; i < xForces.length; i = i + 1) {
      xForces[i] = array[i].calcNetForceExertedByX(array);
      }
    for (int i = 0; i < yForces.length; i = i + 1) {
      yForces[i] = array[i].calcNetForceExertedByY(array);
    }

  StdDraw.clear();
  StdDraw.picture(0, 0, "images/starfield.jpg");
  for (int i = 0; i < array.length; i = i + 1) {
    array[i].update(dt, xForces[i], yForces[i]);
    array[i].draw();
  }
  StdDraw.show(10);
  time = time + dt;
}
StdOut.printf("%d\n", array.length);
StdOut.printf("%.2e\n", radius);
for (int i = 0; i < array.length; i++) {
  StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
  array[i].xxPos, array[i].yyPos, array[i].xxVel, array[i].yyVel, array[i].mass, array[i].imgFileName);
}
}

public static double readRadius(String fileName) {
    In txt = new In(fileName);
    double numPlanets = txt.readDouble();
    double radius = txt.readDouble();
    return radius;
}

public static Planet[] readPlanets(String fileName) {
  In files = new In(fileName);
  int numPlanets = files.readInt();
  files.readDouble();
  Planet[] array = new Planet[numPlanets];
  int i = 0;
  while(i < array.length) {
    double xxPos = files.readDouble();
    double yyPos = files.readDouble();
    double xxVel = files.readDouble();
    double yyVel = files.readDouble();
    double mass = files.readDouble();
    String imgFileName = files.readString();
    Planet currPlanet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
    array[i] = currPlanet;
    currPlanet.draw();
    i += 1;
}
return array;
}
}
