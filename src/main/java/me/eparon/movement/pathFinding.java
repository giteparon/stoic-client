package me.eparon.movement;
import net.minecraft.core.BlockPos;
import java.util.ArrayList;
import net.minecraft.Minecraft;
import me.eparon.environment.Position;
import me.eparon.movement.BlockNode;
import me.eparon.environment.blockUtils;
import java.util.*;
public class pathFinding
{
    public static final int[][] DIRECTIONS = {
            // 6 Face neighbors (directly adjacent)
            { 1,  0,  0}, {-1,  0,  0}, // X axis
            { 0,  1,  0}, { 0, -1,  0}, // Y axis
            { 0,  0,  1}, { 0,  0, -1}, // Z axis

            // 12 Edge neighbors (diagonal on a plane)
            { 1,  1,  0}, { 1, -1,  0}, {-1,  1,  0}, {-1, -1,  0}, // XY plane
            { 1,  0,  1}, { 1,  0, -1}, {-1,  0,  1}, {-1,  0, -1}, // XZ plane
            { 0,  1,  1}, { 0,  1, -1}, { 0, -1,  1}, { 0, -1, -1}, // YZ plane

            // 8 Corner neighbors (full diagonal)
            { 1,  1,  1}, { 1,  1, -1}, { 1, -1,  1}, { 1, -1, -1},
            {-1,  1,  1}, {-1,  1, -1}, {-1, -1,  1}, {-1, -1, -1},
    };

	public static ArrayList<BlockNode> pathFind(BlockPos pos) {
        //using A* haha god save me (thank you ai for explaining how ts works :sob:)
        Minecraft mc = Minecraft.getInstance();
        double tX  = pos.getX();
        double tY = pos.getY();
        double tZ = pos.getZ();
        double sX = mc.player.getX();
        double sY = mc.player.getY();
        double sZ = mc.player.getZ();
        Position startPos = new Position(sX, sY, sZ);
        Position goalPos = new Position(tX, tY, tZ);
        BlockNode startNode = new BlockNode(startPos, goalPos, null);
        BlockNode currNode = startNode;
        waypointsArray.add(startPos);
        PriorityQueue<BlockNode> openSet = new PriorityQueue<>();
        int counter = 0;
        //MAKE A HASHMAP AT THE END TO CALCULATYE PATH
        Map<String, int[]> parent = new HashMap<>();
        openSet.add(startNode);
        while (!openSet.isEmpty()) {
            BlockNode current = openSet.poll();
            // Goal reached
            if (current.x == goalPos.x && current.y == goalPos.y && current.z = goalPos.z) break;

            for (int[] dir : DIRECTIONS) {
                BlockNode newNode = new BlockNode(current.x + dir[0], current.y + dir[1], current.z + dir[2], goalPos, current);
                if(newNode.checkValidity()){
                    openSet.add(newNode);
                    parent.put(nx + "," + ny, new int[]{current.x, current.y})
                }
            }
            counter++;
            if(counter == 10000){
                System.out.println("too many checks twin");
                return null;
            }

        }


}