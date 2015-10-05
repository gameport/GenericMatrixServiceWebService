package com.ram.spring.matrix.rest;

import com.ram.spring.matrix.api.GenericMatrixService;
import com.ram.spring.matrix.model.Matrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gamport.
 */
@RestController
@RequestMapping("/matrixService")
public class GenericMatrixRestService {

	@Autowired
	GenericMatrixService matrixService;

	@RequestMapping(value = "/getMatrix/{matrixName}", method = RequestMethod.GET)
	@ResponseBody
	public Matrix getMatrix(@PathVariable String matrixName) {
		Matrix matrix = null;
		matrix = matrixService.getMatrixByMatrixName(matrixName);

		return matrix;
	}

	@RequestMapping(value = "/addMatrix", method = RequestMethod.PUT)
	@ResponseBody
	public long addMatrix(@RequestParam(value = "matrixName", required = true) String matrixName,
	                      @RequestParam(value = "positionX", required = true) int positionX,
	                      @RequestParam(value = "positionY", required = true) int positionY,
	                      @RequestParam(value = "dataType", required = true) String dataType,
	                      @RequestParam(value = "dataValue", required = true) String dataValue) {
		Matrix matrix = new Matrix();
		matrix.setMatrixName(matrixName);
		matrix.setPositionX(positionX);
		matrix.setPositionY(positionY);
		matrix.setDataType(dataType);
		matrix.setDataValue(dataValue);

		long matrixId = matrixService.addMatrix(matrix);

		return matrixId;
	}

	@RequestMapping(value = "/updateMatrix", method = RequestMethod.POST)
	@ResponseBody
	public long updateMatrix(@RequestParam(value = "matrixId", required = true) long matrixId,
	                      @RequestParam(value = "matrixName", required = true) String matrixName,
	                      @RequestParam(value = "positionX", required = true) int positionX,
	                      @RequestParam(value = "positionY", required = true) int positionY,
	                      @RequestParam(value = "dataType", required = true) String dataType,
	                      @RequestParam(value = "dataValue", required = true) String dataValue) {
		Matrix matrix = new Matrix();
		matrix.setMatrixId(matrixId);
		matrix.setMatrixName(matrixName);
		matrix.setPositionX(positionX);
		matrix.setPositionY(positionY);
		matrix.setDataType(dataType);
		matrix.setDataValue(dataValue);

		matrixService.updateMatrix(matrix);

		return matrixId;
	}

	@RequestMapping(value = "/deleteMatrix", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteMatrix(@RequestParam(value="matrixName", required=true) String matrixName) {
		matrixService.deleteMatrix(matrixName);

		return true;
	}

}
