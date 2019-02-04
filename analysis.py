from rap import dict

import numpy as np
from matplotlib import pyplot as plt
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

colors = 'red'
area = np.pi*3

# Plot
pos = np.arange(len(y))
plt.scatter(x, y, s=area, c=colors, alpha=0.5)
ycoords = [3000, 4000, 5000, 6000, 7000]
line_colors = ['darkred','darkorange','yellow', 'yellowgreen', 'green']
for yc,c in zip(ycoords,line_colors):
    plt.axhline(y=yc, label='line at y = {}'.format(yc), c=c)
plt.ylim([2000,8000])
plt.xticks(pos, x, rotation='vertical')
plt.ylabel('Vocab Count', fontsize=16)
plt.xlabel('Artist', fontsize=16)
plt.title('# of Unique Words Used Within Artist’s First 35,000 Lyrics',fontsize=20)
plt.show()
