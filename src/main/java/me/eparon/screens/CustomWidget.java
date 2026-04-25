package me.eparon.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

// :::1
public class CustomWidget extends AbstractWidget {
    public boolean displayHovered;
    private Minecraft minecraft =  Minecraft.getInstance();
    private String hoveredText;
    private String text;
    public boolean removedWidget = true;
    public TextBoxWidget hovered = new TextBoxWidget(0, 0, 120, 20, "Default Text");
    //private

    public CustomWidget(int x, int y, int width, int height, String text, String hoveredText) {
        super(x, y, width, height, Component.empty());
        this.text = text;
        hovered.setText(hoveredText);


    }



    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        //Button buttonWidget = Button.builder(Component.literal("Hello World"), (btn) -> {StoicClient.LOGGER("clicked button");}).bounds(40, 40, 120, 20).build();
        // We'll just draw a simple rectangle for now.
        // x1, y1, x2, y2, startColor, endColor
        int startColor = 0xFF00FF00; // Green
        int endColor = 0xFF0000FF; // Blue
        // :::1

        // :::2
        // This is in the "renderWidget" method, so we can check if the mouse is hovering over the widget.
        if (isHovered()) {
            startColor = 0xFFFF0000; // Red
            endColor = 0xFF00FFFF;
            this.hovered.setPosition(mouseX, mouseY);
                    //=  new TextBoxWidget(mouseX, mouseY, 120, 20, "Hello");
            displayHovered = true;


        }
        else{

            displayHovered = false;
        }
        // :::2
        // :::1
        //Button buttonWidget = Button.builder(Component.literal("Hello World"), (btn) -> {StoicClient.LOGGER("clicked button");}).bounds(40, 40, 120, 20).build();
        graphics.fillGradient(getX(), getY(), getX() + this.width, getY() + this.height, startColor, endColor);
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
        // For brevity, we'll just skip this for now - if you want to add narration to your widget, you can do so here.
        return;
    }
}
// :::1