import ttg
import pandas as pd

def generate_truth_table(variables, expressions):
    try:
        variables_list = variables.split(",")
        expressions_list = expressions.split(",")

        table = ttg.Truths(variables_list, expressions_list, ints=False)
        df = table.as_pandas()

        # Rename columns to replace operators with symbols
        replacements = {
            'and': '∧',
            'or': '∨',
            'not': '¬',
            '=>': '→',
            'implies': '→',
            'xor': '⊕',
            'nand': '⊼',
            '=': '↔'
        }

        df.columns = [replacements.get(col, col) for col in df.columns]

        # Convert DataFrame to HTML with styling
        html_table = df.to_html(index=False, escape=False)

        # Adding CSS for styling
        styled_html = f"""
        <html>
        <head>
        <style>
        table {{
            border-collapse: collapse;
            width: 100%;
            font-family: Arial, sans-serif;
        }}
        th, td {{
            border: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }}
        th {{
            background-color: #4CAF50;
            color: white;
        }}
        tr:nth-child(even) {{
            background-color: #f2f2f2;
        }}
        tr:nth-child(odd) {{
            background-color: #ffffff;
        }}
        </style>
        </head>
        <body>
        {html_table}
        </body>
        </html>
        """

        return styled_html
    except Exception as e:
        return str(e)
