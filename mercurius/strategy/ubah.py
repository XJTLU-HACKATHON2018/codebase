from strategy import expert
import numpy as np
import tools
import logging

class ubah(expert):
    '''Uniform Buy and Hold'''
    def __init__(self):
        super(ubah, self).__init__()


    def get_b(self, data, last_b):
        return last_b


if __name__ == '__main__':
    tools.run(ubah(),plot=True)
