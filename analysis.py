from rap import dict

import numpy as np
from matplotlib import pyplot as plt
import pandas as pd
from collections import OrderedDict
from matplotlib.offsetbox import OffsetImage, AnnotationBbox

#colours in matplotlib:
#https://matplotlib.org/gallery/color/named_colors.html#sphx-glr-gallery-color-named-colors-py

# lists x and y are used for plotting the data
x = []
y = []
# updates the values to the actual vocab values (len(set(value))), the value is accessed with dict[key]
dict.update((key,len(set(dict[key]))) for key in dict )
print(dict)

#sorts the dictionary in asc order of values
dict = OrderedDict(sorted(dict.items(), key=lambda t: t[1]))
print(dict)

for key, value in dict.items():
    x.append(key)
    y.append(value)

print(x)
print(y)


# Adds horizontal lines and fills them up
ycoords = [3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000]
line_colors = ['#800000','brown','tab:red',
'darkorange','sandybrown', 'khaki','#ffff99','#ccff99', '#99ff66', '#66ff66', '#009933']
for yc,c in zip(ycoords,line_colors):
    plt.axhspan(ymin = yc-500, ymax = yc, xmin=0, xmax=1, color=c)

plt.axhline(y=7000, label='line at y = {}'.format(7000), c='green')
plt.axhline(y=5000, label='line at y = {}'.format(7000), c='red')

# Plot
color = 'black'
area = np.pi*4
pos = np.arange(len(y))
# zorder attribute determines position of different layers
plt.scatter(x, y, s=area, c=color, alpha=0.5, zorder=2)

plt.ylim([3000,8000])
plt.xticks(pos, x, rotation='vertical')
plt.ylabel('Vocab Count', fontsize=16)
plt.xlabel('Artist', fontsize=16)
plt.title('# of Unique Words Used Within Artist’s First 35,000 Lyrics',fontsize=20)
plt.show()
