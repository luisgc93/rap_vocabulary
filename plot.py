#https://realpython.com/python-matplotlib-guide/

import matplotlib.pyplot as plt
import numpy as np


fig, ax = plt.subplots()
# range from 0 to 50
rng = np.arange(50)
# generates random number
rnd = np.random.randint(0, 10, size=(3, rng.size))
yrs = 1950 + rng

fig, ax = plt.subplots(figsize=(5, 3))
ax.stackplot(yrs, rng + rnd, labels=['Eastasia', 'Eurasia', 'Oceania'])
ax.set_title('Combined debt growth over time')
ax.legend(loc='upper left')
ax.set_ylabel('Total debt')
ax.set_xlim(left=yrs[0], right=yrs[-1])
fig.tight_layout()
