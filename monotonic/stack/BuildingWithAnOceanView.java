package monotonic.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
https://leetcode.com/problems/buildings-with-an-ocean-view/description/
There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the
buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without
obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
=======================================================================================================================
find the closest larger number on the right side.
monotonic stack, large to small.
For the current building, keep popping out stack top if top is smaller or equal to the current building.
Those remains in the stack has ocean view.

 */
public class BuildingWithAnOceanView {
    public int[] buildingsWithOceanView(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop();
            }
            stack.push(i);
        }

        int i = stack.size() - 1;
        int[] res = new int[stack.size()];
        while (!stack.isEmpty()) {
            res[i--] = stack.pop();
        }

        return res;
    }

    public int[] buildingsWithOceanView_right_to_left(int[] heights) {
        List<Integer> buildings = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] > heights[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                buildings.add(i);
            }
            stack.push(i);
        }

        int count = buildings.size();
        int[] res = new int[buildings.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = buildings.get(count - i - 1);
        }

        return res;
    }

    public int[] buildingWithOceanView_Stack_with_array(int[] heights) {
        int top = 0;
        int[] stack = new int[10000];
        for (int i = 0; i < heights.length; i++) {
            while (top > 0 && heights[i] >= heights[stack[top - 1]]) {
                top--;
            }
            stack[top++] = i;
        }

        int[] res = new int[top];
        while (top > 0) {
            res[top] = stack[top - 1];
            top--;
        }

        return res;
    }

    public int[] buildingWithOceanView_optimize_space(int[] heights) {
        int highest = 0;
        List<Integer> buildings = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > highest) {
                buildings.add(i);
                highest = heights[i];
            }
        }

        int count = buildings.size();
        int[] res = new int[count];
        for (int i = 0; i < res.length; i++) {
            res[i] = buildings.get(count - i - 1);
        }

        return res;
    }
}
