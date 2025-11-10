package br.com.fiap.clinicamedica.resource;

import br.com.fiap.clinicamedica.dao.PacienteDao;
import br.com.fiap.clinicamedica.dto.paciente.PacienteCadastroDto;
import br.com.fiap.clinicamedica.dto.paciente.PacienteResponseDto;
import br.com.fiap.clinicamedica.model.Paciente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid; // Import para o @Valid
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @Inject
    PacienteDao pacienteDao;

    @Inject
    ModelMapper modelMapper;

    @POST
    public Response cadastrar(@Valid PacienteCadastroDto pacienteDto) {
        Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);


        Paciente pacienteSalvo = pacienteDao.cadastrar(paciente);


        PacienteResponseDto responseDto = modelMapper.map(pacienteSalvo, PacienteResponseDto.class);


        URI uri = UriBuilder.fromPath("/pacientes/{id}")
                .build(responseDto.getId());
        return Response.created(uri).entity(responseDto).build();
    }

    @GET
    public Response listarTodos() {
        List<Paciente> pacientes = pacienteDao.listar();


        List<PacienteResponseDto> dtos = pacientes.stream()
                .map(paciente -> modelMapper.map(paciente, PacienteResponseDto.class))
                .collect(Collectors.toList());

        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    public Response pesquisarPorId(@PathParam("id") long id) {
        Paciente paciente = pacienteDao.pesquisarPorId(id);


        PacienteResponseDto responseDto = modelMapper.map(paciente, PacienteResponseDto.class);
        return Response.ok(responseDto).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") long id, @Valid PacienteCadastroDto pacienteDto) {
        Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);
        paciente.setId(id);

        Paciente pacienteAtualizado = pacienteDao.atualizar(paciente);

        PacienteResponseDto responseDto = modelMapper.map(pacienteAtualizado, PacienteResponseDto.class);
        return Response.ok(responseDto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") long id) {
        pacienteDao.remover(id);
        return Response.noContent().build();
    }
}
