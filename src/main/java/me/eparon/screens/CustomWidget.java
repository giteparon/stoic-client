package me.eparon.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;
import me.eparon.StoicClient;
import me.eparon.themes.*;
import net.minecraft.client.gui.components.SplashRenderer;
// :::1
public class CustomWidget extends AbstractWidget {
    public boolean displayHovered;
    public boolean clicked = false;
    private Minecraft minecraft =  Minecraft.getInstance();
    private Font font = minecraft.font;
    private String hoveredText;
    private String text;
    private int color = Default.WIDGETCOLOR;
    private boolean isHoverable = false;
    private int endColor = -1;
    public boolean removedWidget = true;
    public TextBoxWidget hovered = new TextBoxWidget(0, 0, 120, 20, "Default Text");
    public SplashRenderer splashRenderer;

    //private
    public CustomWidget(int x, int y, int width, int height, String text) {
        super(x, y, width, height, Component.empty());
        this.text = text;

    }
    public CustomWidget(int x, int y, int width, int height, String text,boolean isHoverable, String hoveredText) {
        super(x, y, width, height, Component.empty());
        this.text = text;
        this.isHoverable = isHoverable;
        hovered.setText(hoveredText);

    }
    public CustomWidget(int x, int y, int width, int height, String text,boolean isHoverable, String hoveredText, int color) {
        super(x, y, width, height, Component.empty());
        this.text = text;
        this.isHoverable = isHoverable;
        this.color = color;
        hovered.setText(hoveredText);

    }
    public CustomWidget(int x, int y, int width, int height, String text,boolean isHoverable, String hoveredText, int color, int endcolor) {
        super(x, y, width, height, Component.empty());
        this.text = text;
        this.color = color;
        this.endColor = endcolor;
        this.isHoverable = isHoverable;
        hovered.setText(hoveredText);



    }



    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float delta) {

        int startColor = this.color;
        int endColor = this.endColor;
        if (isHovered() && this.isHoverable) {
            String _hexColor = Integer.toHexString(startColor);
            _hexColor = "55" + _hexColor.substring(_hexColor.length() - 6, _hexColor.length());
            startColor = Integer.parseInt(_hexColor, 16);
            this.hovered.setPosition(mouseX, mouseY);
            displayHovered = true;
        }
        else{
            displayHovered = false;
        }
        if(endColor != -1){
            graphics.fillGradient(getX(), getY(), getX() + this.width, getY() + this.height, startColor, endColor);

        }
        else{
            graphics.fillGradient(getX(), getY(), getX() + this.width, getY() + this.height, startColor, startColor);
        }

        graphics.drawString(minecraft.font,
                text,
                getX() + this.width /  2 - minecraft.font.width(text) / 2,
                getY() + this.height / 2 - minecraft.font.lineHeight / 2,
                0xFFFFFFFF,
                true
        );


    }
    public boolean getState(){
        return clicked;
    }
    public void setColor(int color) {
        this.color = color;
    }
    @Override
    public void onClick(final MouseButtonEvent event, final boolean doubleClick){
        if(!clicked){
            setColor(0xFF00FF00);
            clicked = true;
        }
        else{
            setColor(Default.WIDGETCOLOR);
            clicked = false;
        }


    }
    @Override
    protected void updateWidgetNarration(NarrationElementOutput builder) {
        return;
    }
}