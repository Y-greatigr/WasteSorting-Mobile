package com.cookandroid.sorting_mobile

import android.graphics.Bitmap
import com.cookandroid.sorting_mobile.Utils.assetFilePath
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import org.pytorch.torchvision.TensorImageUtils


class Classifier(modelPath: String?) {
    var model: Module
    var mean = floatArrayOf(0.485f, 0.456f, 0.406f)
    var std = floatArrayOf(0.229f, 0.224f, 0.225f)
    fun setMeanAndStd(mean: FloatArray, std: FloatArray) {
        this.mean = mean
        this.std = std
    }

    fun preprocess(bitmap: Bitmap?, size: Int): Tensor {
        var bitmap = bitmap
        bitmap = Bitmap.createScaledBitmap(bitmap!!, size, size, false)
        return TensorImageUtils.bitmapToFloat32Tensor(bitmap, mean, std)
    }

    fun argMax(inputs: FloatArray): Int {
        var maxIndex = -1
        var maxvalue = 0.0f
        for (i in inputs.indices) {
            if (inputs[i] > maxvalue) {
                maxIndex = i
                maxvalue = inputs[i]
            }
        }
        return maxIndex
    }

    fun predict(bitmap: Bitmap?): String {
        val tensor = preprocess(bitmap, 224)
        val inputs = IValue.from(tensor)
        val outputs = model.forward(inputs).toTensor()
        val scores = outputs.dataAsFloatArray
        val classIndex = argMax(scores)
        return Constants.IMAGENET_CLASSES[classIndex]
    }

    init {
        model = Module.load(modelPath)
    }
}