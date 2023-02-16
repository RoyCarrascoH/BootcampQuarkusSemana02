package nttdata.bootcamp.quarkus.loan;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nttdata.bootcamp.quarkus.loan.dto.LoanResponse;
import nttdata.bootcamp.quarkus.loan.dto.ResponseBase;
import nttdata.bootcamp.quarkus.loan.entity.LoanEntity;
import nttdata.bootcamp.quarkus.loan.service.LoanService;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/api/loan")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleResource {

    private static final Logger LOGGER = Logger.getLogger(ExampleResource.class.getName());

    @Inject
    private LoanService loanService;

    @GET
    public LoanResponse getLoans() {
        LoanResponse response = new LoanResponse();
        List<LoanEntity> loan = loanService.listAll();
        if (loan == null) {
            response.setCodigoRespuesta(2);
            response.setMensajeRespuesta("Respuesta nula");
            response.setLoan(null);
        } else if (loan.size() == 0) {
            response.setCodigoRespuesta(1);
            response.setMensajeRespuesta("No existen prestamos");
            response.setLoan(loan);
        } else {
            response.setCodigoRespuesta(0);
            response.setMensajeRespuesta("Respuesta Exitosa");
            response.setLoan(loan);
        }
        return response;
    }

    @GET
    @Path("{idLoan}")
    public LoanEntity viewLoanDetails(@PathParam("idLoan") Long idLoan) {
        LoanEntity entity = loanService.findById(idLoan);
        if (entity == null) {
            throw new WebApplicationException("Client with id of " + idLoan + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(LoanEntity loan) {
        loanService.save(loan);
        return Response.ok(loan).status(201).build();
    }

    @DELETE
    @Path("{idLoan}")
    @Transactional
    public ResponseBase delete(@PathParam("idLoan") Long idLoan) {
        ResponseBase response = new ResponseBase();
        LoanEntity entity = loanService.findById(idLoan);
        if (entity == null) {
            response.setCodigoRespuesta(1);
            response.setMensajeRespuesta("Id de Loan Card no existe");
            throw new WebApplicationException("Loan with id of " + idLoan + " does not exist.", 404);
        } else {
            response.setCodigoRespuesta(0);
            response.setMensajeRespuesta("Eliminacion exitosa de loan id = " + idLoan);
            loanService.delete(idLoan);
        }

        return response;
    }

    @PUT
    @Path("{idLoan}")
    @Transactional
    public LoanEntity updateClient(@PathParam("idLoan") Long idLoan, LoanEntity loan) {

        LoanEntity entity = loanService.findById(idLoan);
        if (entity == null) {
            throw new WebApplicationException("Loan with id of " + idLoan + " does not exist.", 404);
        }
        entity.setLoanNumber(loan.getLoanNumber());
        entity.setMonthlyPaymentDate(loan.getMonthlyPaymentDate());
        entity.setExpirationDate(loan.getExpirationDate());
        entity.setQuotaNumber(loan.getQuotaNumber());
        entity.setValidationCode(loan.getValidationCode());
        entity.setInitialBalance(loan.getInitialBalance());
        entity.setCurrentBalance(loan.getCurrentBalance());
        entity.setAmountOfFeesPaid(loan.getAmountOfFeesPaid());
        entity.setQuotaAmount(loan.getQuotaAmount());
        entity.setClient(loan.getClient());
        loanService.save(entity);
        return entity;
    }
}