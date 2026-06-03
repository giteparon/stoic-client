package me.eparon.movement;
import me.eparon.StoicClient;
import net.minecraft.client.Minecraft;
public class RotUtils  {
    public static boolean macroRotating = false;
    public static void sleepMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static void smoothRotate(float yaw, float pitch) {
        Minecraft mc =  Minecraft.getInstance();

        Thread lookThread = new Thread(() -> {
            float iRotY = mc.player.yRotO;
            float iRotX = mc.player.xRotO;
            int pitchAlt;
            int yawAlt;
            if(mc.player.xRotO > pitch) {
                pitchAlt = -1;
            }
            else{
                pitchAlt = 1;
            }
            float diff = wrapDegrees(yaw - mc.player.yRotO);
            yawAlt = diff > 0 ? 1 : -1;
            while (Math.round(iRotY) != Math.round(yaw) || Math.round(iRotX) != Math.round(pitch)) {
               // System.out.println("yaw: " + Math.round(mc.player.getYRot()) + "expected yaw: " + Math.round(yaw));
                macroRotating = true;
                if(Math.round(iRotY) != Math.round(yaw)) {
                    iRotY += 1 * yawAlt;
                }

                if(iRotY >= 180) {
                    iRotY -= 360;
                }
                else if(iRotY <= -180) {
                    iRotY += 360;
                }

                mc.player.setYRot(iRotY);
                if(Math.round(iRotX) != Math.round(pitch)) {
                    iRotX += 1 * pitchAlt;
                }

                if(iRotX > 90 || iRotX < -90) {
                    pitchAlt *= -1;
                }
                mc.player.setXRot(iRotX);

                sleepMs(4);

            }
            macroRotating = false;

        });

        lookThread.setName("(Rotating)");
        lookThread.start();

    }
    public static void smoothLookAt(double targetX, double targetY, double targetZ) {
        Minecraft mc = Minecraft.getInstance();
        double playerX = mc.player.getX();
        double playerY = mc.player.getEyeY();
        double playerZ = mc.player.getZ();

        double dX = targetX - playerX;
        double dY = targetY - playerY;
        double dZ = targetZ - playerZ;

        double distanceXZ = Math.sqrt(dX * dX + dZ * dZ);

        float yaw = (float) (Math.toDegrees(Math.atan2(-dX, dZ)));
        if( yaw < -180) {
            yaw += 360;
        }
        else if(yaw > 180 ){
            yaw -= 360;
        }
        float pitch = (float) (-Math.toDegrees(Math.atan2(dY, distanceXZ)));

        // 5. Apply the rotation using your defined method
        System.out.println("Yaw: " + yaw + " Pitch: " + pitch);
        smoothRotate(yaw,pitch);
    }
    private static float wrapDegrees(float degrees) {
        degrees %= 360f;
        if (degrees > 180f)  degrees -= 360f;
        if (degrees < -180f) degrees += 360f;
        return degrees;
    }


}