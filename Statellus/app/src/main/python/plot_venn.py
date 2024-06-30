import io
import matplotlib.pyplot as plt
import numpy as np
# Ensure you have this line to use the venn diagram
from matplotlib_venn import venn3

def plot_venn(x, y):
    try:
        # Parse input values
        xa = set(float(word) for word in x.split())
        ya = set(float(word) for word in y.split())

        # Calculate subsets
        a_only = xa - ya
        b_only = ya - xa
        intersection = xa & ya

        # Modify the data based on your requirements
        venn_data = (len(a_only), len(b_only), len(intersection),
                     len(b_only), len(intersection), len(a_only),
                     len(intersection), len(a_only), len(b_only))

        fig, ax = plt.subplots()

        # Venn diagram
        venn3(subsets=venn_data, ax=ax)

        f = io.BytesIO()
        plt.savefig(f, format="png")
        plt.close(fig)
        return f.getvalue()
    except Exception as e:
        return str(e)