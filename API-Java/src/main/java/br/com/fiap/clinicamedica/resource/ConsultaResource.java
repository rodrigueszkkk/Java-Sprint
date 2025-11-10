package br.com.fiap.clinicamedica.resource;

import br.com.fiap.clinicamedica.dao.ConsultaDao;
import br.com.fiap.clinicamedica.dto.consulta.ConsultaCadastroDto;
import br.com.fiap.clinicamedica.dto.consulta.ConsultaResponseDto;
import br.com.fiap.clinicamedica.model.Consulta;
import br.com.fiap.clinicamedica.model.Medico;
import br.com.fiap.clinicamedica.model.Paciente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    ConsultaDao consultaDao;

    @Inject
    ModelMapper modelMapper;

    @POST
    public Response agendar(@Valid ConsultaCadastroDto consultaDto) {

        Consulta consulta = modelMapper.map(consultaDto, Consulta.class);

        Paciente pacienteStub = new Paciente();
        pacienteStub.setId(consultaDto.getIdPaciente());

        Medico medicoStub = new Medico();
        medicoStub.setId(consultaDto.getIdMedico());

        consulta.setPaciente(pacienteStub);
        consulta.setMedico(medicoStub);

        Consulta consultaAgendada = consultaDao.cadastrar(consulta);

        ConsultaResponseDto responseDto = modelMapper.map(consultaAgendada, ConsultaResponseDto.class);

        URI uri = UriBuilder.fromPath("/consultas/{id}").build(responseDto.getId());
        return Response.created(uri).entity(responseDto).build();
    }

    @GET
    public Response listarTodas() {
        List<Consulta> consultas = consultaDao.listar();

        List<ConsultaResponseDto> dtos = consultas.stream()
                .map(consulta -> modelMapper.map(consulta, ConsultaResponseDto.class))
                .collect(Collectors.toList());

        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    public Response pesquisarPorId(@PathParam("id") long id) {
        Consulta consulta = consultaDao.pesquisarPorId(id);
        ConsultaResponseDto responseDto = modelMapper.map(consulta, ConsultaResponseDto.class);
        return Response.ok(responseDto).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") long id, @Valid ConsultaCadastroDto consultaDto) {
        Consulta consulta = modelMapper.map(consultaDto, Consulta.class);

        consulta.setId(id);

        Paciente pacienteStub = new Paciente();
        pacienteStub.setId(consultaDto.getIdPaciente());
        consulta.setPaciente(pacienteStub);

        Medico medicoStub = new Medico();
        medicoStub.setId(consultaDto.getIdMedico());
        consulta.setMedico(medicoStub);

        Consulta consultaAtualizada = consultaDao.atualizar(consulta);
        ConsultaResponseDto responseDto = modelMapper.map(consultaAtualizada, ConsultaResponseDto.class);
        return Response.ok(responseDto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response cancelar(@PathParam("id") long id) {
        consultaDao.remover(id);
        return Response.noContent().build();
    }
}