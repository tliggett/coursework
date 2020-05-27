package homework2;

class Circle
{
    private double radius;
    public Circle()
    {
        radius = 0.0;
    }
    public Circle(double rad)
    {
        radius = rad;
    }
    public double getRadius()
    {
        return this.radius;
    }
    public String toString()
    {
        return "Radius: " + radius;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof Circle)
        {
            Circle c=(Circle)obj;
            if (radius==c.radius) return true;
            else return false;
        }
        return false;
    }
}
