package eu.ha3.matmos.game.system;


/**
 * @author dags_ <dags@dags.me>
 */

public class MAtMutableBlockPos
{
    private int x;
    private int y;
    private int z;

    public MAtMutableBlockPos()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public MAtMutableBlockPos of(int xPos, int yPos, int zPos)
    {
        x = xPos;
        y = yPos;
        z = zPos;
        return this;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getZ()
    {
        return this.z;
    }
}
