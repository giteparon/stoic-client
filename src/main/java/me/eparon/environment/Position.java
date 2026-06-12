package me.eparon.environment;
import net.minecraft.core.BlockPos;
public class Position {
    public int x;
    public int y;
    public int z;
    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setZ(int z) {
        this.z = z;
    }
    public BlockPos getPosition() {
        return new BlockPos(this.x, this.y, this.z);
    }
    @Override
    public String toString() {
        return "Position [x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
    }
}