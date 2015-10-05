package com.ram.spring.matrix.soap.endpoint;

import com.ram.spring.matrix.api.GenericMatrixService;
import com.ram.spring.matrix.model.Matrix;
import com.ram.spring.matrix.soap.jaxb.GetMatrixRequest;
import com.ram.spring.matrix.soap.jaxb.GetMatrixResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by gamport.
 */
@Endpoint
public class MatrixEndpoint {

	private static final String NAMESPACE_URI = "http://com.ram.spring/ws/matrixOperations";

	@Autowired
	GenericMatrixService matrixService;

	/**
	 * get the matrix details using the name
	 */

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMatrixRequest")
	@ResponsePayload
	public GetMatrixResponse getMatrix(@RequestPayload GetMatrixRequest request) {
		GetMatrixResponse response = new GetMatrixResponse();
		String name = request.getMatrixName();
		System.out.println("MatrixRequest ::" + request.toString());
		System.out.println("getMatrixName ::" + name);

		if(name==null || name=="") {
			name = "Second";
		}
		System.out.println("getMatrixName ::" + name);
		Matrix matrix = matrixService.getMatrixByMatrixName(name);

		System.out.println("matrix ::" + matrix.toString());

		com.ram.spring.matrix.soap.jaxb.Matrix soapMatrix = new com.ram.spring.matrix.soap.jaxb.Matrix();
		soapMatrix.setMatrixId(matrix.getMatrixId());
		soapMatrix.setDataType(matrix.getDataType());
		soapMatrix.setDataValue(matrix.getDataValue());
		soapMatrix.setPositionX(matrix.getPositionX());
		soapMatrix.setPositionY(matrix.getPositionY());
		soapMatrix.setMatrixName(matrix.getMatrixName());

		response.setMatrix(soapMatrix);

		return response;
	}
}
