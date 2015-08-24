#######################################################################################
## Assignment 3                                                                      ##
## Discussion is allowed.                                                            ##
## Copying or sharing code is prohibited.                                            ##
## By typing your name below, you indicate agreement with the UNC Honor Code Pledge, ##
## that you have not given or received unauthorized assistance on this assignment.   ##
## Name: Vir Desai                                                                            ##
## Collaborators:                                                                    ## 
#######################################################################################

import numpy as np
import pylab

# Some helpers to load and save images
# You won't need to modify these
def load_binary_image(name):
    '''Load a binary image from a file'''
    im = pylab.imread(name)
    if im.ndim == 3:
        result = im[:, :, 0] != 0
    elif im.ndim == 2:
        result = im != 0
    return result

def save_binary_image(name, value):
    '''Save a binary image to a file'''
    pylab.imsave(name, value, cmap=pylab.gray())

# Load the test image we'll use below
test_image = load_binary_image('test.png')

# 1. Scroll images.

# Define your functions to scroll images

def scrollLeft(img, amt):
    '''Return a new image that is the given image scrolled left by amt pixels'''
    new = np.zeros_like(img)
    new[:,:-amt] = img[:,amt:]
    # Do something to fill in values in new here
    return new

# Similarly define the rest of them.

def scrollRight(img, amt):
    '''Return a new image that is the given image scrolled right by amt pixels'''
    new = np.zeros_like(img)
    new[:,amt:] = img[:,:-amt]
    # Do something to fill in values in new here
    return new

def scrollUp(img, amt):
    '''Return a new image that is the given image scrolled up by amt pixels'''
    new = np.zeros_like(img)
    new[:-amt,:] = img[amt:,:]
    # Do something to fill in values in new here
    return new

def scrollDown(img, amt):
    '''Return a new image that is the given image scrolled down by amt pixels'''
    new = np.zeros_like(img)
    new[amt:,:] = img[:-amt,:] 
    # do something to fill in values in new here
    return new

# 1A. Scroll the image up by 10 pixels.
up10 = scrollUp(test_image, 10)
# And save that
save_binary_image('1A.png', up10)

# 1B. Now scroll the result of 1A down by 10 pixels.
updown10 = scrollDown(up10, 10)
# And save that
save_binary_image('1B.png', updown10)

# 1C. Do they look the same? Why or why not?

print """They are similar but not the same because the image used in part 1B comes from 1A. The image is edited in 1A so that the top part of the head is outside of the image range; therefore the remaining part of the image is the only part used for editing in 1B."""

# 1D. Scroll the test image left by half its width
left_half = scrollLeft(test_image, (len(test_image[0,:]))/2)
# Save it in 1D.png as above
save_binary_image('1D.png', left_half)

# 1E. And then scroll that result back to the right by the same amount.
leftright = scrollRight(left_half, (len(test_image[0,:]))/2)
# Save in 1E.png.
save_binary_image('1E.png', leftright)

# 2. Boundary Detector

def findBoundary(img):
    '''Return an image that is one for boundary pixels and zero elsewhere'''
    # Compute the result of finding the boundary here
    result = np.zeros_like(img)  # Replace this with your code
    R_b = np.logical_and(img==1, scrollRight(img,1)==0)
    L_b = np.logical_and(img==1, scrollLeft(img,1)==0)
    U_b = np.logical_and(img==1, scrollUp(img,1)==0)
    D_b = np.logical_and(img==1, scrollDown(img,1)==0)
    br1 = np.logical_or(R_b==1, L_b==1)
    br2 = np.logical_or(U_b==1, D_b==1)
    result = np.logical_or(br1, br2)        
    
    return result

# Save the boundary of the test image

boundary = findBoundary(test_image)
save_binary_image('2.png', boundary)

# 3. Dilate and Erode

def dilateImage(img):
    '''Return an image that is the dilation of the input img'''
	# Compute the dilation here and return it below
    result = np.zeros_like(img)  # Replace this with your code
    L_d = np.logical_and(img==0, scrollLeft(img,1)==1)
    R_d = np.logical_and(img==0, scrollRight(img,1)==1)
    U_d = np.logical_and(img==0, scrollUp(img,1)==1)
    D_d = np.logical_and(img==0, scrollDown(img,1)==1)
    dr1 = np.logical_or(L_d==1, R_d==1)
    dr2 = np.logical_or(U_d==1, D_d==1)
    dilate = np.logical_or(dr1, dr2)
    result = np.logical_or(img==1, dilate==1)

    return result


def erodeImage(img):
    '''Return an image that is the erosion of the input img'''
	# Compute the value of the erosion and return it below
    result = np.zeros_like(img)  # Replace this with your code
    result=np.logical_and(img==1, findBoundary(img)==0)

    return result

# 3A. Dilate the test image, then dilate that result, then dilate that result so you have
# three dilations. Save the final result.

D3 = dilateImage(dilateImage(dilateImage(test_image)))
save_binary_image('3A.png', D3)

E3 = erodeImage(erodeImage(erodeImage(test_image)))
save_binary_image('3B.png', E3)

E3D3 = dilateImage(dilateImage(dilateImage(E3)))
save_binary_image('3C.png', E3D3)

# 3D. Find the boundary of the original image, and then dilate the boundary image.  Save the result.
BD = dilateImage(findBoundary(test_image))

# save as 3D.png
save_binary_image('3D.png', BD)

# 3E. Then take the original image, dilate it once, and then find its boundary.  Save the result.
DB = findBoundary(dilateImage(test_image))

# save as 3E.png
save_binary_image('3E.png', DB)

# 3F. Describe the difference between the two images.

print """Picture 3D is the boundary of the original image plus the points directly to the outside of the boundary are true. So double the thickness. 3E is the image shrunk by 1 unit and the bounary of that. 3E would be the boundary of 3D and is half as thick."""
