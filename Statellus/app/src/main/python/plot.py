import io
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.colors import LinearSegmentedColormap
from matplotlib_venn import venn2
import stemgraphic

def plot(x, y):
    """
    Plot the data provided as comma-separated values.

    :param x: String containing comma-separated x values
    :param y: String containing comma-separated y values
    :return: Bytes representing the PNG image of the plot
    :raises ValueError: If input data format is invalid
    """
    try:
        xa = [float(word) for word in x.split(',')]
        ya = [float(word) for word in y.split(',')]
    except ValueError:
        raise ValueError("Invalid input format. Please provide comma-separated numeric values.")

    fig, ax = plt.subplots()
    ax.plot(xa, ya)

    # Save the plot to a BytesIO object
    f = io.BytesIO()
    plt.savefig(f, format="png")
    plt.close()  # Close the plot to release resources
    return f.getvalue()

def generate_stem_and_leaf(data_str):
    """
    Generate a stem-and-leaf plot for the given data.

    :param data_str: String containing comma-separated numeric values
    :return: Bytes representing the PNG image of the stem-and-leaf plot
    :raises ValueError: If input data format is invalid
    """
    try:
        # Split the input string by commas and convert each element to float
        data_list = [float(x.strip()) for x in data_str.split(',') if x.strip()]

        # Generate stem chart with specified display parameter and customizations
        fig, ax = stemgraphic.graphic.stem_graphic(data_list,aggregation=False)

        # Save the plot to a BytesIO object
        plot_bytes = io.BytesIO()
        plt.savefig(plot_bytes, format="png", bbox_inches='tight')
        plt.close(fig)
        return plot_bytes.getvalue()
    except ValueError:
        raise ValueError("Invalid input data. Please provide comma-separated numeric values.")

def generate_box_plot(data, color_start='#EC3CAB', color_end='#0B40C5', frame_width=2):
    """
    Generate a box plot for the given data.

    :param data: String containing comma-separated numeric values
    :param color_start: Starting color for the gradient
    :param color_end: Ending color for the gradient
    :param frame_width: Width of the box plot frame
    :return: Bytes representing the PNG image of the box plot
    :raises ValueError: If input data format is invalid
    """
    try:
        data_list = [float(x) for x in data.split(',') if x.strip()]
        fig, ax = plt.subplots()
        ax.boxplot(data_list)

        # Define a gradient color map with two colors
        cmap = LinearSegmentedColormap.from_list('custom', [color_start, color_end])

        # Apply the gradient color to the spines
        for spine in ax.spines.values():
            spine.set_color(cmap(0.5))  # Use the midpoint color from the gradient
            spine.set_linewidth(frame_width)  # Set the width of the frame

        plot_bytes = io.BytesIO()
        plt.savefig(plot_bytes, format="png")
        plt.close(fig)
        return plot_bytes.getvalue()
    except ValueError:
        raise ValueError("Invalid input data. Please enter comma-separated numeric values.")

def plot_venn(a, b, labels=['A', 'B'], color_start='#EC3CAB', color_end='#0B40C5'):
    """
    Generate a Venn diagram for two sets.

    :param a: String containing comma-separated numeric values for set A
    :param b: String containing comma-separated numeric values for set B
    :param labels: Labels for the sets
    :param color_start: Starting color for the gradient
    :param color_end: Ending color for the gradient
    :return: Bytes representing the PNG image of the Venn diagram
    :raises ValueError: If input data format is invalid
    """
    try:
        a_set = set(map(float, a.split(',')))
        b_set = set(map(float, b.split(',')))

        only_a = len(a_set - b_set)
        only_b = len(b_set - a_set)
        a_b = len(a_set & b_set)

        fig, ax = plt.subplots()
        venn_diagram = venn2(subsets=(only_a, only_b, a_b), set_labels=labels)

        # Define the custom colormap for the gradient
        cmap = LinearSegmentedColormap.from_list('custom', [color_start, color_end])

        # Set the face color of the Venn diagram patches using the colormap
        venn_diagram.get_patch_by_id('10').set_facecolor(color_start)  # Circle A
        venn_diagram.get_patch_by_id('01').set_facecolor(color_end)    # Circle B
        venn_diagram.get_patch_by_id('11').set_facecolor(cmap(0.5))    # Intersection AB

        # Convert the figure to bytes
        f = io.BytesIO()
        plt.savefig(f, format="png")
        plt.close(fig)
        return f.getvalue()

    except ValueError:
        raise ValueError("Invalid input data. Please provide comma-separated numeric values.")






