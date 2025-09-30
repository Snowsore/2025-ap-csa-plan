package org.example.core;

public class InputState {
    public double thrust; // -1..+1
    public double yaw, pitch, roll; // -1..+1 each
    public boolean reset;
    public boolean fire;
}
