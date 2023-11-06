import {
    AlertDialog,
    AlertDialogAction,
    AlertDialogCancel,
    AlertDialogContent,
    AlertDialogDescription,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogTitle,
} from '@/components/ui/alert-dialog';
import {Badge} from '@/components/ui/badge';
import {CollapsibleTrigger} from '@/components/ui/collapsible';
import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';
import {
    HoverCard,
    HoverCardContent,
    HoverCardTrigger,
} from '@/components/ui/hover-card';
import {Switch} from '@/components/ui/switch';
import {Tooltip, TooltipContent, TooltipTrigger} from '@/components/ui/tooltip';
import {
    ProjectInstanceModel,
    ProjectModel,
    TagModel,
} from '@/middleware/helios/configuration';
import {
    useDeleteProjectInstanceMutation,
    useEnableProjectInstanceMutation,
    useUpdateProjectInstanceTagsMutation,
} from '@/mutations/projectInstances.mutations';
import {useProjectInstancesEnabledStore} from '@/pages/automation/project-instances/stores/useProjectInstancesEnabledStore';
import {ProjectInstanceKeys} from '@/queries/projectInstances.queries';
import {ChevronDownIcon, DotsVerticalIcon} from '@radix-ui/react-icons';
import {useQueryClient} from '@tanstack/react-query';
import {CalendarIcon} from 'lucide-react';
import {useState} from 'react';
import {twMerge} from 'tailwind-merge';

import TagList from '../../../components/TagList/TagList';
import ProjectInstanceDialog from './ProjectInstanceDialog';

interface ProjectItemProps {
    projectInstance: ProjectInstanceModel;
    remainingTags?: TagModel[];
    project: ProjectModel;
}

const ProjectInstanceListItem = ({
    project,
    projectInstance,
    remainingTags,
}: ProjectItemProps) => {
    const [showEditDialog, setShowEditDialog] = useState(false);
    const [showDeleteDialog, setShowDeleteDialog] = useState(false);
    const setProjectInstanceEnabled = useProjectInstancesEnabledStore(
        ({setProjectInstanceEnabled}) => setProjectInstanceEnabled
    );

    const queryClient = useQueryClient();

    const deleteProjectInstanceMutation = useDeleteProjectInstanceMutation({
        onSuccess: () => {
            queryClient.invalidateQueries(ProjectInstanceKeys.projectInstances);
            queryClient.invalidateQueries(
                ProjectInstanceKeys.projectInstanceTags
            );
        },
    });

    const updateProjectInstanceTagsMutation =
        useUpdateProjectInstanceTagsMutation({
            onSuccess: () => {
                queryClient.invalidateQueries(
                    ProjectInstanceKeys.projectInstances
                );
                queryClient.invalidateQueries(
                    ProjectInstanceKeys.projectInstanceTags
                );
            },
        });

    const enableProjectInstanceMutation = useEnableProjectInstanceMutation({
        onSuccess: () => {
            queryClient.invalidateQueries(ProjectInstanceKeys.projectInstances);
        },
    });

    return (
        <>
            <div className="flex items-center justify-between">
                <div className="w-10/12">
                    <div className="flex items-center justify-between">
                        <div className="flex w-full items-center justify-between">
                            {projectInstance.description ? (
                                <HoverCard>
                                    <HoverCardTrigger>
                                        {projectInstance.description}
                                    </HoverCardTrigger>

                                    <HoverCardContent>
                                        <span className="mr-2 text-base font-semibold">
                                            {projectInstance.name}
                                        </span>
                                    </HoverCardContent>
                                </HoverCard>
                            ) : (
                                <span className="mr-2 text-base font-semibold">
                                    {projectInstance.name}
                                </span>
                            )}
                        </div>

                        <div className="ml-2 flex shrink-0">
                            <Badge
                                className={twMerge(
                                    projectInstance.enabled &&
                                        'bg-success text-success-foreground hover:bg-success'
                                )}
                                variant="secondary"
                            >
                                {projectInstance.enabled
                                    ? 'Enabled'
                                    : 'Disabled'}
                            </Badge>
                        </div>
                    </div>

                    <div className="mt-2 sm:flex sm:items-center sm:justify-between">
                        <div className="flex items-center">
                            <CollapsibleTrigger className="group mr-4 flex text-xs font-semibold text-gray-700">
                                <span className="mr-1">
                                    {project.workflowIds?.length === 1
                                        ? `1 workflow`
                                        : `${project.workflowIds?.length} workflows`}
                                </span>

                                <ChevronDownIcon className="h-4 w-4 duration-300 group-data-[state=open]:rotate-180" />
                            </CollapsibleTrigger>

                            <div onClick={(event) => event.preventDefault()}>
                                {projectInstance.tags && (
                                    <TagList
                                        id={projectInstance.id!}
                                        remainingTags={remainingTags}
                                        tags={projectInstance.tags}
                                        updateTagsMutation={
                                            updateProjectInstanceTagsMutation
                                        }
                                        getRequest={(id, tags) => ({
                                            id: id!,
                                            updateTagsRequestModel: {
                                                tags: tags || [],
                                            },
                                        })}
                                    />
                                )}
                            </div>
                        </div>

                        <div className="mt-2 flex items-center text-sm text-gray-500 sm:mt-0">
                            <Tooltip>
                                <TooltipTrigger>
                                    {projectInstance.lastExecutionDate ? (
                                        <div className="flex text-sm text-gray-500">
                                            <CalendarIcon
                                                className="mr-1 h-5 w-5 shrink-0 text-gray-400"
                                                aria-hidden="true"
                                            />

                                            <span>
                                                {`${projectInstance.lastExecutionDate?.toLocaleDateString()} ${projectInstance.lastExecutionDate?.toLocaleTimeString()}`}
                                            </span>
                                        </div>
                                    ) : (
                                        '-'
                                    )}
                                </TooltipTrigger>

                                <TooltipContent>
                                    Last Execution Date
                                </TooltipContent>
                            </Tooltip>
                        </div>
                    </div>
                </div>

                <div className="flex w-1/12 items-center justify-end">
                    <Switch
                        checked={projectInstance.enabled}
                        onCheckedChange={(value) => {
                            enableProjectInstanceMutation.mutate(
                                {
                                    enable: value,
                                    id: projectInstance.id!,
                                },
                                {
                                    onSuccess: () => {
                                        setProjectInstanceEnabled(
                                            projectInstance.id!,
                                            !projectInstance.enabled
                                        );
                                        projectInstance!.enabled =
                                            !projectInstance.enabled;
                                    },
                                }
                            );
                        }}
                    />
                </div>

                <div className="flex w-1/12 justify-end">
                    <DropdownMenu>
                        <DropdownMenuTrigger asChild>
                            <DotsVerticalIcon className="h-4 w-4 hover:cursor-pointer" />
                        </DropdownMenuTrigger>

                        <DropdownMenuContent align="end">
                            <DropdownMenuItem
                                className="cursor-pointer text-xs text-gray-700"
                                onClick={() => setShowEditDialog(true)}
                            >
                                Edit
                            </DropdownMenuItem>

                            <DropdownMenuSeparator />

                            <DropdownMenuItem
                                className="cursor-pointer text-xs text-red-600"
                                onClick={() => setShowDeleteDialog(true)}
                            >
                                Delete
                            </DropdownMenuItem>
                        </DropdownMenuContent>
                    </DropdownMenu>
                </div>
            </div>

            {showDeleteDialog && (
                <AlertDialog open={showDeleteDialog}>
                    <AlertDialogContent>
                        <AlertDialogHeader>
                            <AlertDialogTitle>
                                Are you absolutely sure?
                            </AlertDialogTitle>

                            <AlertDialogDescription>
                                This action cannot be undone. This will
                                permanently delete the project and workflows it
                                contains.
                            </AlertDialogDescription>
                        </AlertDialogHeader>

                        <AlertDialogFooter>
                            <AlertDialogCancel
                                onClick={() => setShowDeleteDialog(false)}
                            >
                                Cancel
                            </AlertDialogCancel>

                            <AlertDialogAction
                                className="bg-red-600"
                                onClick={() => {
                                    if (projectInstance.id) {
                                        deleteProjectInstanceMutation.mutate(
                                            projectInstance.id
                                        );
                                    }
                                }}
                            >
                                Delete
                            </AlertDialogAction>
                        </AlertDialogFooter>
                    </AlertDialogContent>
                </AlertDialog>
            )}

            {showEditDialog && (
                <ProjectInstanceDialog
                    projectInstance={projectInstance}
                    showTrigger={false}
                    visible
                    onClose={() => setShowEditDialog(false)}
                />
            )}
        </>
    );
};

export default ProjectInstanceListItem;
