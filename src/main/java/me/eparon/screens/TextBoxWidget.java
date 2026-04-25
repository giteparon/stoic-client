package me.eparon.screens;
import java.awt.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

// :::1
public class TextBoxWidget extends AbstractWidget {
    private Minecraft minecraft =  Minecraft.getInstance();
    private String text;
//    private Font font = new Font("Serif", Font.BOLD, 24);

    public TextBoxWidget(int x, int y, int width, int height, String s) {
        super(x, y, width, height, Component.literal(s));
        text = s;
    }
    public void setText(String s) {
        text = s;
    }
    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {

        int color = 0x55000000;
        graphics.fill(getX(), getY(), getX() + this.width, getY() + this.height,color);
        graphics.drawString(minecraft.font,
                text,
                getX() + this.width /  2 - minecraft.font.width(text) / 2,
                getY() + this.height / 2 - minecraft.font.lineHeight / 2,
                0xFFFFFFFF,
                true
        );

    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput builder) {
        return;
    }
}
// :::1