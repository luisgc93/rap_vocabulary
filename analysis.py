from rap import dict

import numpy as np
from matplotlib import pyplot as plt
import pandas as pd
from collections import OrderedDict
# Used in image display
from matplotlib.offsetbox import OffsetImage, AnnotationBbox
# Used for roundcropping images
import matplotlib.patches as patches

#colours in matplotlib:
#https://matplotlib.org/gallery/color/named_colors.html#sphx-glr-gallery-color-named-colors-py


# used for plotting images
def getImage(path,zoom=0.1):
    return OffsetImage(plt.imread(path),zoom=zoom)
# image paths
paths = [
    'images/control_machete.jpg','images/bad_bunny.jpg','images/cervantes.jpg','images/vico_c.jpg',
    'images/santa_rm.jpg','images/chojin.jpg','images/tego_calderon.jpg','images/cartel_de_santa.jpg',
    'images/tres_coronas.jpg', 'images/duo_kie.jpg','images/residente.jpg','images/porta.jpg',
    'images/rapsusklei.jpg', 'images/aldeanos.jpg','images/falsa_alarma.jpg','images/kase_o.jpg',
    'images/akil_ammar.jpg','images/tote_king.jpg','images/violadores_del_verso.jpg','images/akapellah.jpg',
    'images/nach.jpg','images/sfdk.jpg' ]


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

# unpacks tuple into figure and axis
fig, ax = plt.subplots()

# Adds horizontal lines and fills them up
ycoords = [3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000]
line_colors = ['#800000','brown','tab:red',
'darkorange','sandybrown', 'khaki','#ffff99','#ccff99', '#99ff66', '#66ff66', '#009933']
for yc,c in zip(ycoords,line_colors):
    ax.axhspan(ymin = yc-500, ymax = yc, xmin=0, xmax=1, color=c)



ax.scatter(x, y)
artists = []
for x0, y0, path in zip(x, y,paths):
    ab = AnnotationBbox(getImage(path), (x0, y0), frameon=False)
    artists.append(ax.add_artist(ab))

ax.axhline(y=7000, label='line at y = {}'.format(7000), c='green')
ax.axhline(y=5000, label='line at y = {}'.format(7000), c='red')

# Plot
color = 'black'
area = np.pi*2
pos = np.arange(len(y))
# zorder attribute determines position of different layers
plt.scatter(x, y, s=area, c=color, alpha=0.5, zorder=2)

plt.ylim([3000,8000])
plt.xticks(pos, x, rotation='vertical')
plt.ylabel('Vocab Count', fontsize=16)
plt.xlabel('Artist', fontsize=16)
plt.title('# of Unique Words Used Within Artist’s First 35,000 Lyrics',fontsize=20)

plt.show()
