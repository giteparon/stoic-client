package me.eparon.environment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import java.util.ArrayList;
import me.eparon.environment.Position;
public class blockUtils {
    //Block{minecraft:name}
    public static BlockPos findBlockInRadiusByString(String name , int r2){
        Player player = Minecraft.getInstance().player;

        Position startPos = new Position((int)player.getX(), (int)player.getY(), (int)player.getZ());
        Position targetPos = startPos;
        Position iPos = startPos;
        boolean found = false;
        ArrayList<Position> checked = new ArrayList<Position>();
        int counter = 0;
        int r = 1;

        if(checkBlock(startPos.x, startPos.y, startPos.z, name)){found = true; targetPos = startPos;}
        while(!found){
            if(checkBlock(startPos.x + r, startPos.y, startPos.z, name) && !(checked.contains(new Position(startPos.x + r, startPos.y, startPos.z)))){found = true; targetPos = new Position(startPos.x + r, startPos.y, startPos.z); break;}
            else{checked.add(new Position(startPos.x + r, startPos.y, startPos.z));}
            if(checkBlock(startPos.x + r, startPos.y, startPos.z + r, name) && !(checked.contains(new Position(startPos.x + r, startPos.y, startPos.z + r)))){found = true; targetPos = new Position(startPos.x + r, startPos.y, startPos.z + r);break;}
            else{checked.add(new Position(startPos.x + r, startPos.y , startPos.z + r));}
            if(checkBlock(startPos.x + r, startPos.y, startPos.z - r, name) && !(checked.contains(new Position(startPos.x + r, startPos.y, startPos.z - r)))){found = true; targetPos = new Position(startPos.x + r, startPos.y, startPos.z - r);break;}
            else{checked.add(new Position(startPos.x + r, startPos.y, startPos.z - r));}
            //-----------------
            if(checkBlock(startPos.x, startPos.y, startPos.z + r, name) && !(checked.contains(new Position(startPos.x, startPos.y, startPos.z + r)))){found = true; targetPos = new Position(startPos.x, startPos.y, startPos.z + r);break;}
            else{checked.add(new Position(startPos.x, startPos.y , startPos.z + r));}
            if(checkBlock(startPos.x, startPos.y, startPos.z - r, name) && !(checked.contains(new Position(startPos.x, startPos.y, startPos.z - r)))){found = true; targetPos = new Position(startPos.x, startPos.y, startPos.z - r);break;}
            else{checked.add(new Position(startPos.x, startPos.y, startPos.z - r));}
            //----------------------
            if(checkBlock(startPos.x - r, startPos.y, startPos.z + r, name) && !(checked.contains(new Position(startPos.x - r, startPos.y, startPos.z + r)))){found = true; targetPos = new Position(startPos.x - r, startPos.y, startPos.z + r);break;}
            else{checked.add(new Position(startPos.x - r, startPos.y , startPos.z + r));}
            if(checkBlock(startPos.x - r, startPos.y, startPos.z - r, name) && !(checked.contains(new Position(startPos.x - r, startPos.y, startPos.z - r)))){found = true; targetPos = new Position(startPos.x - r, startPos.y, startPos.z - r);break;}
            else{checked.add(new Position(startPos.x - r, startPos.y, startPos.z - r));}
            if(checkBlock(startPos.x - r, startPos.y, startPos.z, name) && !(checked.contains(new Position(startPos.x - r, startPos.y, startPos.z)))){found = true; targetPos = new Position(startPos.x - r, startPos.y, startPos.z);break;}
            else{checked.add(new Position(startPos.x - r, startPos.y, startPos.z));}
            // y + r ----------------------------
            if(checkBlock(startPos.x + r, startPos.y + r, startPos.z, name) && !(checked.contains(new Position(startPos.x + r, startPos.y + r, startPos.z)))){found = true; targetPos = new Position(startPos.x + r, startPos.y + r, startPos.z);break;}
            else{checked.add(new Position(startPos.x + r, startPos.y + r, startPos.z));}
            if(checkBlock(startPos.x + r, startPos.y + r, startPos.z + r, name) && !(checked.contains(new Position(startPos.x + r, startPos.y + r, startPos.z + r)))){found = true; targetPos = new Position(startPos.x + r, startPos.y + r, startPos.z + r);break;}
            else{checked.add(new Position(startPos.x + r, startPos.y + r , startPos.z + r));}
            if(checkBlock(startPos.x + r, startPos.y + r, startPos.z - r, name) && !(checked.contains(new Position(startPos.x + r, startPos.y + r, startPos.z - r)))){found = true; targetPos = new Position(startPos.x + r, startPos.y + r, startPos.z - r);break;}
            else{checked.add(new Position(startPos.x + r, startPos.y + r, startPos.z - r));}
            //-----------------
            if(checkBlock(startPos.x, startPos.y + r, startPos.z + r, name) && !(checked.contains(new Position(startPos.x, startPos.y + r, startPos.z + r)))){found = true; targetPos = new Position(startPos.x, startPos.y + r, startPos.z + r);break;}
            else{checked.add(new Position(startPos.x, startPos.y + r , startPos.z + r));}
            if(checkBlock(startPos.x, startPos.y + r, startPos.z, name) && !(checked.contains(new Position(startPos.x, startPos.y + r, startPos.z)))){found = true; targetPos = new Position(startPos.x, startPos.y + r, startPos.z);break;}
            else{checked.add(new Position(startPos.x, startPos.y + r, startPos.z));}
            if(checkBlock(startPos.x, startPos.y + r, startPos.z - r, name) && !(checked.contains(new Position(startPos.x, startPos.y + r, startPos.z - r)))){found = true; targetPos = new Position(startPos.x, startPos.y + r, startPos.z - r);break;}
            else{checked.add(new Position(startPos.x, startPos.y + r, startPos.z - r));}
            //----------------------
            if(checkBlock(startPos.x - r, startPos.y + r, startPos.z + r, name) && !(checked.contains(new Position(startPos.x - r, startPos.y + r, startPos.z + r)))){found = true; targetPos = new Position(startPos.x - r, startPos.y + r, startPos.z + r);break;}
            else{checked.add(new Position(startPos.x - r, startPos.y + r , startPos.z + r));}
            if(checkBlock(startPos.x - r, startPos.y + r, startPos.z - r, name) && !(checked.contains(new Position(startPos.x - r, startPos.y + r, startPos.z - r)))){found = true; targetPos = new Position(startPos.x - r, startPos.y + r, startPos.z - r);break;}
            else{checked.add(new Position(startPos.x - r, startPos.y + r, startPos.z - r));}
            if(checkBlock(startPos.x - r, startPos.y + r, startPos.z, name) && !(checked.contains(new Position(startPos.x - r, startPos.y + r, startPos.z)))){found = true; targetPos = new Position(startPos.x - r, startPos.y + r, startPos.z);break;}
            else{checked.add(new Position(startPos.x - r, startPos.y + r, startPos.z));}
            // y - r-----------------------
            if(checkBlock(startPos.x + r, startPos.y - r, startPos.z, name) && !(checked.contains(new Position(startPos.x + r, startPos.y - r, startPos.z)))){found = true; targetPos = new Position(startPos.x + r, startPos.y - r, startPos.z);break;}
            else{checked.add(new Position(startPos.x + r, startPos.y - r, startPos.z));}
            if(checkBlock(startPos.x + r, startPos.y - r, startPos.z + r, name) && !(checked.contains(new Position(startPos.x + r, startPos.y - r, startPos.z + r)))){found = true; targetPos = new Position(startPos.x + r, startPos.y - r, startPos.z + r);break;}
            else{checked.add(new Position(startPos.x + r, startPos.y - r , startPos.z + r));}
            if(checkBlock(startPos.x + r, startPos.y - r, startPos.z - r, name) && !(checked.contains(new Position(startPos.x + r, startPos.y - r, startPos.z - r)))){found = true; targetPos = new Position(startPos.x + r, startPos.y - r, startPos.z - r);break;}
            else{checked.add(new Position(startPos.x + r, startPos.y - r, startPos.z - r));}
            //-----------------
            if(checkBlock(startPos.x, startPos.y - r, startPos.z + r, name) && !(checked.contains(new Position(startPos.x, startPos.y - r, startPos.z + r)))){found = true; targetPos = new Position(startPos.x, startPos.y - r, startPos.z + r);break;}
            else{checked.add(new Position(startPos.x, startPos.y - r , startPos.z + r));}
            if(checkBlock(startPos.x, startPos.y - r, startPos.z, name) && !(checked.contains(new Position(startPos.x, startPos.y - r, startPos.z)))){found = true; targetPos = new Position(startPos.x, startPos.y - r, startPos.z);break;}
            else{checked.add(new Position(startPos.x, startPos.y - r, startPos.z));}
            if(checkBlock(startPos.x, startPos.y - r, startPos.z - r, name) && !(checked.contains(new Position(startPos.x, startPos.y - r, startPos.z - r)))){found = true; targetPos = new Position(startPos.x, startPos.y - r, startPos.z - r);break;}
            else{checked.add(new Position(startPos.x, startPos.y - r, startPos.z - r));}
            //----------------------
            if(checkBlock(startPos.x - r, startPos.y - r, startPos.z + r, name) && !(checked.contains(new Position(startPos.x - r, startPos.y - r, startPos.z + r)))){found = true; targetPos = new Position(startPos.x - r, startPos.y - r, startPos.z + r);break;}
            else{checked.add(new Position(startPos.x - r, startPos.y - r , startPos.z + r));}
            if(checkBlock(startPos.x - r, startPos.y - r, startPos.z - r, name) && !(checked.contains(new Position(startPos.x - r, startPos.y - r, startPos.z - r)))){found = true; targetPos = new Position(startPos.x - r, startPos.y - r, startPos.z - r);break;}
            else{checked.add(new Position(startPos.x - r, startPos.y - r, startPos.z - r));}
            if(checkBlock(startPos.x - r, startPos.y - r, startPos.z, name) && !(checked.contains(new Position(startPos.x - r, startPos.y - r, startPos.z)))){found = true; targetPos = new Position(startPos.x - r, startPos.y - r, startPos.z);break;}
            else{checked.add(new Position(startPos.x - r, startPos.y - r, startPos.z));}

            startPos = checked.get(counter);
            counter++;
            if(startPos.x == iPos.x + r2){
                System.out.println("no block found");
                break;

            }



        }
        return targetPos.getPosition();
    }
    private static boolean checkBlock(int x, int y, int z, String name){

        Level level = Minecraft.getInstance().level;
        BlockPos targetPos = new BlockPos(x,y,z);
        BlockState state = level.getBlockState(targetPos);
        Block block = state.getBlock();
        if(block.toString().equals(name)){
            return true;
        }
        return false;
    }
}