package me.eparon.movement;
import me.eparon.environment.Position;
import net.minecraft.core.BlockPos;
import me.eparon.environment.blockUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
public class BlockNode {
    public Position pos;
    public BlockNode parent;
    public float startCost; // cost from start
    public float heuristicCost; //using euclidian space - cost till target
    public float totalCost;// for decision making like youre gonna need to check this
    public boolean checked = false;
    public BlockNode(Position pos, Position goal, BlockNode parent){
        this.pos = pos;
        this.parent = parent;
        if(this.parent == null){
            this.startCost = 0;
        }
        else {
            this.startCost = parent.startCost + this.calculateCostOfThis();
        }
        this.heuristicCost = sqrt((goal.y-pos.x)*(goal.x-pos.x) + (goal.y-pos.y)*(goal.y-pos.y) + (goal.z-pos.z)*(goal.z-pos.z));
        this.totalCost = startCost + heuristicCost;
    }
    public BlockNode(int x, int y, int z, Position goal, BlockNode parent){
        this.pos = new Position(x, y, z);
        this.parent = parent;
        if(this.parent == null){
            this.startCost = 0;
        }
        else {
            this.startCost = parent.startCost + this.calculateCostOfThis();
        }
        this.heuristicCost = sqrt((goal.y-pos.x)*(goal.x-pos.x) + (goal.y-pos.y)*(goal.y-pos.y) + (goal.z-pos.z)*(goal.z-pos.z));
        this.totalCost = startCost + heuristicCost;
    }
    public float calculateCostOfThis(){
        float cost = 0;
        Block block = blockUtils.getBlock(this.pos);
        switch(block.toString()){
            case "Block{minecraft:water}":
                cost += 1.0f;
                break;
            case "Block{minecraft:lava}":
                cost += 1.0f;
                break;
        }
        return cost;
    }
    public Position getPosition(){
        return this.pos;
    }
    public float getCost(){
        return this.totalCost;
    }
    public void debug(){
        System.out.println(this.pos.toString() + "total cost:" + this.totalCost);
    }
    public boolean checkValidity(){
        if(checked = false){
            this.checked = true;
            return blockUtils.getBlock(this.getPosition()).toString().equals("Block{minecraft:air}");
        }
        else{
            return false;
        }



    }
}